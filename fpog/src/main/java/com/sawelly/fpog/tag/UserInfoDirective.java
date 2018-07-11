package com.sawelly.fpog.tag;

import com.sawelly.fpog.entity.User;
import com.sawelly.fpog.service.UserService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class UserInfoDirective implements TemplateDirectiveModel {

    private static final Logger log = LoggerFactory.getLogger(UserInfoDirective.class);

    @Autowired
    private UserService userService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        User user = new User();
        if (map.containsKey("id") && map.get("id") != null) {
            String id = map.get("id").toString();
            user = userService.selectById(Integer.parseInt(id));
        }
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        environment.setVariable("user", builder.build().wrap(user));
        templateDirectiveBody.render(environment.getOut());
    }
}
