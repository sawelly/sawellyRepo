//package com.sawelly.fpog.common.config;
//
//import cn.org.rapid_framework.freemarker.directive.BlockDirective;
//import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
//import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import javax.annotation.PostConstruct;
//import java.nio.charset.Charset;
//import java.util.List;
//
//@Configuration
//public class CustomMVCConfiguration extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(
//                Charset.forName("UTF-8"));
//        return converter;
//    }
//
//    @Override
//    public void configureMessageConverters(
//            List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(responseBodyConverter());
//    }
//
//    @Override
//    public void configureContentNegotiation(
//            ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(false);
//    }
//}
