package com.sawelly.fpog.controller.system;

import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.ArticleType;
import com.sawelly.fpog.entity.ProductType;
import com.sawelly.fpog.service.ArticleTypeService;
import com.sawelly.fpog.service.ProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article/type")
public class ArticleTypeController extends BaseSystemController {

    private Logger log = LoggerFactory.getLogger(ArticleTypeController.class);

    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping("/index")
    public String index(Model model, ArticleType articleType, Page page) {
        page = articleTypeService.selectArticleTypePage(articleType, page);
        model.addAttribute("page", page);
        model.addAttribute("articleType", articleType);
        return "articletype/article_type_list";
    }

    @RequestMapping("/edit")
    public String add(Model model, ArticleType articleType) {
        if (articleType.getId() != null) {
            articleType = articleTypeService.selectById(articleType.getId());
        }
        model.addAttribute("articleType", articleType);
        return "articletype/article_type_edit";
    }

    @RequestMapping("/save")
    public String save(Model model, ArticleType articleType) {
        articleTypeService.save(articleType);
        addSuccessMessage("保存成功");
        return "redirect:index";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id") Integer id) {
        articleTypeService.del(id);
        addSuccessMessage("删除成功");
        return "redirect:/article/type/index";
    }
}
