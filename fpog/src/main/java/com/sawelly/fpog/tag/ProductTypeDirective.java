package com.sawelly.fpog.tag;

import com.sawelly.fpog.entity.ProductType;
import com.sawelly.fpog.entity.User;
import com.sawelly.fpog.service.ProductTypeService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ProductTypeDirective implements TemplateDirectiveModel {

    private static final Logger log = LoggerFactory.getLogger(ProductTypeDirective.class);
    @Autowired
    private ProductTypeService productTypeService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        ProductType productType = new ProductType();
        if (map.containsKey("typeCode") && map.get("typeCode") != null) {
            String typeCode = map.get("typeCode").toString();
            productType = productTypeService.selectByTypeCode(typeCode);
        }
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        environment.setVariable("productType", builder.build().wrap(productType));
        templateDirectiveBody.render(environment.getOut());
    }
}
