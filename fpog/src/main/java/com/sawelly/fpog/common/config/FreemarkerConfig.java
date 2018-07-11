package com.sawelly.fpog.common.config;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import com.sawelly.fpog.service.ProductTypeService;
import com.sawelly.fpog.tag.ArticleTypeDirective;
import com.sawelly.fpog.tag.ProductTypeDirective;
import com.sawelly.fpog.tag.UserInfoDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
//https://blog.csdn.net/u013262276/article/details/77986070 自定义标签
@Configuration
public class FreemarkerConfig {
    @Autowired
    freemarker.template.Configuration configuration;


    @Autowired
    private UserInfoDirective userInfoDirective;

    @Autowired
    private ProductTypeDirective productTypeDirective;
    @Autowired
    private ArticleTypeDirective articleTypeDirective;

    @PostConstruct
    public void setSharedVariable(){
        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("override", new OverrideDirective());
        configuration.setSharedVariable("extends", new ExtendsDirective());
        configuration.setSharedVariable("user_info", userInfoDirective);
        configuration.setSharedVariable("product_type", productTypeDirective);
        configuration.setSharedVariable("article_type", articleTypeDirective);
    }

}
