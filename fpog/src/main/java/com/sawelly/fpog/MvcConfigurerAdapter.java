package com.sawelly.fpog;

import com.sawelly.fpog.interceptor.MybatisAppInterceptor;
import com.sawelly.fpog.interceptor.PreparableInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private PreparableInterceptor preparableInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        registry.addInterceptor(preparableInterceptor).addPathPatterns("/**");
    }

}
