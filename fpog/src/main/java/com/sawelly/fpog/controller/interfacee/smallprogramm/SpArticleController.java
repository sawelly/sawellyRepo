package com.sawelly.fpog.controller.interfacee.smallprogramm;

import com.sawelly.fpog.common.CommonConst;
import com.sawelly.fpog.entity.Article;
import com.sawelly.fpog.entity.Project;
import com.sawelly.fpog.service.ArticleService;
import com.sawelly.fpog.service.ProjectService;
import com.sawelly.fpog.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/sp/article")
public class SpArticleController {

    private Logger log = LoggerFactory.getLogger(SpArticleController.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/getArticleById")
    @ResponseBody
    public String getArticleById(Model model, HttpServletRequest request, String id) {
        Article article = articleService.selectById(Integer.parseInt(id));
        article.setRemark(DateFormatUtils.format(article.getCreateDate(),"yyyy-MM-dd"));
        return JsonUtils.objToJson(article);
    }
    @RequestMapping("/getArticleListByCode")
    @ResponseBody
    public String getArticleListByCode(Model model, HttpServletRequest request, String projectCode, String typeCode) {
        List<Article> lst = articleService.selectByTypeCode(projectCode,typeCode);
        for(Article article :lst){
            String content = article.getContent();
            String img = "";
            int imgstart = content.indexOf("<img src=\"")+10;
            int imgend = content.indexOf("\"",imgstart);
            if(imgstart > 0 && imgend >0 && imgend > imgstart){
                img = content.substring(imgstart,imgend);
            }else{
                img = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + CommonConst.defaultImg;
            }
            article.setTypeCode(img);
            article.setRemark(DateFormatUtils.format(article.getCreateDate(),"yyyy-MM-dd"));
        }
        return JsonUtils.objToJson(lst);
    }

    @RequestMapping("/getHomeArticle")
    @ResponseBody
    public String getHomeArticle(Model model, HttpServletRequest request, String projectCode, String typeCode,String count) {
        List<Article> lst = articleService.selectByTypeCodeLimit(projectCode,typeCode,count);
        for(Article article :lst){
            String content = article.getContent();
            String img = "";
            if (StringUtils.isNotBlank(content)){
                int imgstart = content.indexOf("<img src=\"")+10;
                int imgend = content.indexOf("\"",imgstart);
                if(imgstart > 0 && imgend >0 && imgend > imgstart){
                    img = content.substring(imgstart,imgend);
                }else{
                    img = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + CommonConst.defaultImg;
                }
            }else{
                img = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + CommonConst.defaultImg;
            }
            if (!img.startsWith("http")){
                img = CommonConst.DOMAIN + "file/imgshow/" + projectCode + "/" + CommonConst.defaultImg;
            }
            article.setTypeCode(img);
        }
        return JsonUtils.objToJson(lst);
    }


    @RequestMapping("/getArticleByCode")
    @ResponseBody
    public String getArticleByCode(Model model, HttpServletRequest request, String projectCode, String typeCode) {
        return getArticle(projectCode,typeCode);
    }

    @RequestMapping("/getGsjj")
    @ResponseBody
    public String getGsjj(Model model, HttpServletRequest request, String projectCode, String typeCode) {
        return getArticle(projectCode,typeCode);
    }
    @RequestMapping("/getZxtz")
    @ResponseBody
    public String getZxtz(Model model, HttpServletRequest request, String projectCode, String typeCode) {
        return getArticle(projectCode,typeCode);
    }
    private String getArticle(String projectCode, String typeCode) {
        List<Article> lst = articleService.selectByTypeCode(projectCode,typeCode);
        String jsonStr ="";
        if (lst != null && lst.size() > 0) {
            Article article = lst.get(0);
            article.setRemark(DateFormatUtils.format(article.getCreateDate(),"yyyy-MM-dd"));
            jsonStr = JsonUtils.objToJson(article);
        }
        return jsonStr;
    }
}
