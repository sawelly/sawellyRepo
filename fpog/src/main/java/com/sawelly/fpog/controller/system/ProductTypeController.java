package com.sawelly.fpog.controller.system;

import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.ProductType;
import com.sawelly.fpog.service.ProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/type")
public class ProductTypeController extends BaseSystemController {

    private Logger log = LoggerFactory.getLogger(ProductTypeController.class);

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/index")
    public String index(Model model, ProductType productType, Page page) {
        page = productTypeService.selectProductTypePage(productType, page);
        model.addAttribute("page", page);
        model.addAttribute("productType", productType);
        return "producttype/product_type_list";
    }

    @RequestMapping("/edit")
    public String add(Model model, ProductType productType) {
        if (productType.getId() != null) {
            productType = productTypeService.selectById(productType.getId());
        }
        model.addAttribute("productType", productType);
        return "producttype/product_type_edit";
    }

    @RequestMapping("/save")
    public String save(Model model, ProductType productType) {
        productTypeService.save(productType);
        addSuccessMessage("保存成功");
        return "redirect:index";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id") Integer id) {
        productTypeService.del(id);
        addSuccessMessage("删除成功");
        return "redirect:/product/type/index";
    }
}
