package com.sawelly.fpog.controller.system;

import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.Attachment;
import com.sawelly.fpog.entity.Product;
import com.sawelly.fpog.service.AttachmentService;
import com.sawelly.fpog.service.ProductService;
import com.sawelly.fpog.service.ProductTypeService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseSystemController {

    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/index")
    public String index(Model model, Product product, Page page) {
        page = productService.selectProductPage(product, page);
        model.addAttribute("page", page);
        model.addAttribute("product", product);
        return "product/product_list";
    }

    @RequestMapping("/edit")
    public String add(Model model, Product product) {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        wb.dispose()
        if (product.getId() != null) {
            product = productService.selectById(product.getId());
            List<Attachment> fileList = attachmentService.selectByIds(product.getFileids());
            model.addAttribute("fileList", fileList);
        }
        model.addAttribute("product", product);
        model.addAttribute("typeList", productTypeService.selectAll());
        return "product/product_edit";
    }

    @RequestMapping("/save")
    public String save(Model model, Product product) {
        productService.save(product);
        addSuccessMessage("保存成功");
        return "redirect:index";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id") Integer id) {
        productService.del(id);
        addSuccessMessage("删除成功");
        return "redirect:/product/index";
    }
    @RequestMapping("/toTop/{id}")
    public String toTop(@PathVariable("id") Integer id) {
        Product product = productService.selectById(id);
        Date topTime = product.getTopTime();
        if (topTime != null ){//已经置顶过-取消置顶
            product.setTopTime(null);
            addSuccessMessage("取消置顶成功");
        }else{
            product.setTopTime(new Date());
            addSuccessMessage("置顶成功");
        }
        productService.update(product);

        return "redirect:/product/index";
    }
}
