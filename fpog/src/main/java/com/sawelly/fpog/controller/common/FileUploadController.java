package com.sawelly.fpog.controller.common;

import com.sawelly.fpog.common.CommonConst;
import com.sawelly.fpog.common.config.UeditorConfig;
import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.controller.system.BaseSystemController;
import com.sawelly.fpog.entity.Attachment;
import com.sawelly.fpog.entity.Ueditor;
import com.sawelly.fpog.service.AttachmentService;
import com.sawelly.fpog.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileUploadController{

    private static Logger log = LoggerFactory.getLogger(FileUploadController.class);


    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "/imgshow/{projectCode}/{imgName}", method = RequestMethod.GET)
    public String imgUpload(@PathVariable String projectCode,@PathVariable String imgName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            String imgPath = CommonConst.uploadRootDir + projectCode +  "/" + imgName;
            File file = new File(imgPath);
            if (!file.exists()){
                file = new File(CommonConst.uploadRootDir+CommonConst.defaultImg);
            }
            ips = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }
        return null;
    }

    /**
     * 上传附件（一个）
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, HttpServletResponse response) {
        String result = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //MultipartFile multipartFile = multipartRequest.getFile("filename");
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        MultipartFile multipartFile = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            multipartFile = entity.getValue();
            break;
        }
        if (multipartFile != null) {
            Attachment attachment = attachmentService.save(multipartFile);
            result = attachment.getId() + "";
        }
        return JsonUtils.objToJson(result);

    }

    /**
     * 根据附件id删除
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delFile")
    public String delFile(HttpServletRequest request, HttpServletResponse response, Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        try {
            attachmentService.deleteFile(id);
        } catch (Exception e) {
            log.error("删除附件报错fid==" + id, e);
            result.put("result", "failure");
            e.printStackTrace();
        }
        return JsonUtils.objToJson(result);
    }

//    /**
//     * 加载图片
//     *
//     * @param request
//     * @param response
//     */
//    @RequestMapping(value = "/down")
//    public void down(javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {
//        String filePath = uploadFileService.getFilePathById(request.getParameter("id"));
//        String[] suffixes = new String[]{".jpg", ".jpeg", ".png"};
//        boolean isImg = false;
//        for (String suffix : suffixes) {
//            if (filePath.endsWith(suffix)) {
//                isImg = true;
//                break;
//            }
//        }
//        if (!isImg) {
//            filePath = request.getServletContext().getRealPath("/static/img/default-file.png");
//        }
//        try {
//            InputStream is = new FileInputStream(filePath);
//            OutputStream os = response.getOutputStream();
//            byte[] buff = new byte[1024];
//            int length = 0;
//            while ((length = is.read(buff)) > 0) {
//                os.write(buff, 0, length);
//            }
//            os.flush();
//            os.close();
//            is.close();
//        } catch (FileNotFoundException e) {
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }





}
