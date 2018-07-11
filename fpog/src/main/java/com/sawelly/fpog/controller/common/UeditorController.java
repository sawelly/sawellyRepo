package com.sawelly.fpog.controller.common;

import com.alibaba.druid.pool.DruidDataSource;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping("/ueidtor")
public class UeditorController extends BaseSystemController {

    private static Logger log = LoggerFactory.getLogger(UeditorController.class);


    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("/imgupload")
    @ResponseBody
    public String imgUpload(@RequestParam("action") String param, MultipartFile upfile, HttpServletRequest request) {
        Ueditor ueditor = new Ueditor();
        if (param != null && param.equals("config")) {
            return UeditorConfig.UEDITOR_CONFIG;
        } else if (param != null && param.equals("uploadimage") || param.equals("uploadscrawl")) {
            log.info("开始上传图片");
            if (upfile != null) {
                Attachment attachment = attachmentService.save(upfile);
                if (attachment != null) {
                    ueditor.setState("SUCCESS");
                    ueditor.setTitle(attachment.getFilename());
                    ueditor.setUrl(CommonConst.DOMAIN + "file/imgshow/"+ ProjectContextUtils.getProjectContext().getProjectCode() + "/"+attachment.getSavename());
                    ueditor.setOriginal(attachment.getFilename());
                } else {
                    ueditor.setState("save error");
                    return JsonUtils.objToJson(ueditor);
                }
                return JsonUtils.objToJson(ueditor);//uploadImage(upfile,ueditor,request);
            } else {
                ueditor.setState("图片为空");
                return JsonUtils.objToJson(ueditor);
            }
        } else {
            ueditor.setState("不支持该操作");
            return JsonUtils.objToJson(ueditor);
        }
    }


//    @RequestMapping("/upload")
//    public String upload(HttpServletRequest request, HttpServletResponse response)
//            throws IllegalStateException, IOException {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
//                request.getSession().getServletContext());
//        if (multipartResolver.isMultipart(request)) {
//            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//            Iterator<String> iter = multiRequest.getFileNames();
//            while (iter.hasNext()) {
//                MultipartFile file = multiRequest.getFile((String) iter.next());
//                if (file != null) {
//                    String fileName = new Date().getTime() + file.getOriginalFilename();
//                    String path = "e:/springMVC_test/" + fileName;
//                    File localFile = new File(path);
//                    file.transferTo(localFile);
//                }
//            }
//        }
//        return "user/add_user";
//    }


}
