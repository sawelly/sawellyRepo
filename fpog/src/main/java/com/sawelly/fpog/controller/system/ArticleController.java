package com.sawelly.fpog.controller.system;

import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.Article;
import com.sawelly.fpog.service.ArticleService;
import com.sawelly.fpog.service.ArticleTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseSystemController{

    private Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping("/index")
    public String index(Model model, Article article, Page page){
        page = articleService.selectArticlePage(article,page);
        model.addAttribute("page",page);
        model.addAttribute("article",article);
        return "article/article_list";
    }

    @RequestMapping("/edit")
    public String add(Model model, Article article){
        if (article.getId() != null){
            article = articleService.selectById(article.getId());
        }
        model.addAttribute("article",article);
        model.addAttribute("typeList", articleTypeService.selectAll());
        return "article/article_edit";
    }

    @RequestMapping("/save")
    public String save(Model model, Article article){
        articleService.save(article);
        addSuccessMessage("保存成功");
        return "redirect:index";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id") Integer id){
        articleService.del(id);
        addSuccessMessage("删除成功");
        return "redirect:/article/index";
    }
}
