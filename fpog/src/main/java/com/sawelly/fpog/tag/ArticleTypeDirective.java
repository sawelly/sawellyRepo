package com.sawelly.fpog.tag;

import com.sawelly.fpog.entity.ArticleType;
import com.sawelly.fpog.service.ArticleTypeService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ArticleTypeDirective implements TemplateDirectiveModel {

    private static final Logger log = LoggerFactory.getLogger(ArticleTypeDirective.class);
    @Autowired
    private ArticleTypeService articleTypeService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        ArticleType articleType = new ArticleType();
        if (map.containsKey("typeCode") && map.get("typeCode") != null) {
            String typeCode = map.get("typeCode").toString();
            articleType = articleTypeService.selectByTypeCode(typeCode);
        }
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        environment.setVariable("articleType", builder.build().wrap(articleType));
        templateDirectiveBody.render(environment.getOut());
    }
}
