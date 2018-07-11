package com.sawelly.fpog.controller.interfacee.smallprogramm;

import com.sawelly.fpog.common.CommonConst;
import com.sawelly.fpog.entity.Attachment;
import com.sawelly.fpog.entity.Product;
import com.sawelly.fpog.entity.ProductType;
import com.sawelly.fpog.service.AttachmentService;
import com.sawelly.fpog.service.ProductService;
import com.sawelly.fpog.service.ProductTypeService;
import com.sawelly.fpog.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sp/product")
public class SpProductController {

    private Logger log = LoggerFactory.getLogger(SpProductController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("/getBanner")
    @ResponseBody
    public String getProductByCode(Model model, HttpServletRequest request, String projectCode, String typeCode) {
        List<Product> lst = productService.selectByTypeCode(projectCode, typeCode);
        String jsonStr = "";
        if (lst != null && lst.size() > 0) {
            List<Attachment> lstAttachment = attachmentService.selectByIds(lst.get(0).getFileids());
            if (lstAttachment != null && lstAttachment.size() > 0) {
                String imgUrls[] = new String[lstAttachment.size()];
                for (int i = 0; i < lstAttachment.size(); i++) {
                    imgUrls[i] = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + lstAttachment.get(i).getSavename();
                }
                jsonStr = JsonUtils.objToJson(imgUrls);
            }
        }
        return jsonStr;
    }

    /**
     * 获取remark==tab的分类
     *
     * @param model
     * @param request
     * @param projectCode
     * @param remark
     * @return
     */
    @RequestMapping("/getTab")
    @ResponseBody
    public String getTab(Model model, HttpServletRequest request, String projectCode, String remark) {
        List<ProductType> lst = productTypeService.selectByRemark(projectCode, remark);
        String jsonStr = "";
        if (lst != null && lst.size() > 0) {
            Map map = new LinkedHashMap<String, Object>();
//            List arr[] = new ArrayList[lst.size()];
            List lstPro = new ArrayList();
//            Map mapP = new HashMap<String ,Object>();
            map.put("type", lst);
            for (int i = 0; i < lst.size(); i++) {
                List<Product> productLst = productService.selectByTypeCode(projectCode, lst.get(i).getTypeCode());
                String imgUrl = "";
                for (Product product : productLst) {
                    if (product.getProductName().length() > 8 )product.setProductName(product.getProductName().substring(0,8));
                    if (product.getRemark().length() > 8 )product.setRemark(product.getRemark().substring(0,8));
                    if (StringUtils.isNotBlank(product.getFileids())) {
                        List<Attachment> lstAttachment = attachmentService.selectByIds(product.getFileids());
                        imgUrl = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + lstAttachment.get(0).getSavename();
                    } else {
                        imgUrl = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + CommonConst.defaultImg;
                    }
                    if (!imgUrl.startsWith("http")){
                        imgUrl = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + CommonConst.defaultImg;
                    }
                    product.setFileids(imgUrl);
                    product.setRemark(DateFormatUtils.format(product.getCreateDate(),"yyyy-MM-dd"));
                }
                lstPro.add(productLst);
            }
            map.put("product", lstPro);
            jsonStr = JsonUtils.objToJson(map);
            return jsonStr;
        }
        return JsonUtils.objToJson(jsonStr);
    }


    /**
     * 王岩小程程序首页产品
     * @param model
     * @param request
     * @param projectCode
     * @param remark
     * @return
     */
    @RequestMapping("/getHomeProduct")
    @ResponseBody
    public String getHomeProduct(Model model, HttpServletRequest request, String projectCode, String remark) {
        List<Product> lst = productService.selectByRemark(projectCode, remark);
        for (Product product : lst) {
            String imgUrl = "";
            if (StringUtils.isNotBlank(product.getFileids())) {
                List<Attachment> lstAttachment = attachmentService.selectByIds(product.getFileids());
                imgUrl = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + lstAttachment.get(0).getSavename();
            } else {
                imgUrl = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + CommonConst.defaultImg;
            }
            product.setFileids(imgUrl);
        }
        return JsonUtils.objToJson(lst);
    }



    /**
     * @param model
     * @param request
     * @param productId
     * @return
     */
    @RequestMapping("/getByProductId")
    @ResponseBody
    public String getByProductId(Model model, HttpServletRequest request, String productId) {
        Product product = productService.selectById(Integer.parseInt(productId));
        String jsonStr = "";
        if (product != null) {
            if (StringUtils.isNotBlank(product.getFileids())) {
                List<Attachment> lstAttachment = attachmentService.selectByIds(product.getFileids());
                if (lstAttachment != null && lstAttachment.size() > 0) {
                    String imgUrls[] = new String[lstAttachment.size()];
                    for (int i = 0; i < lstAttachment.size(); i++) {
                        imgUrls[i] = CommonConst.DOMAIN + "file/imgshow/" + product.getProjectCode() + "/" + lstAttachment.get(i).getSavename();
                    }
                    product.setImgs(imgUrls);
                }
            }
            jsonStr = JsonUtils.objToJson(product);
            return jsonStr;
        }
        return JsonUtils.objToJson(jsonStr);
    }


}
