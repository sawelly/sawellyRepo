package com.sawelly.fpog.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.sawelly.fpog.common.page.FastMybatisPageDao;
import com.sawelly.fpog.interceptor.MybatisAppInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(DruidConfiguration.class)
public class MybatisConfiguration {

    private Logger log = LoggerFactory.getLogger(MybatisConfiguration.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private DruidDataSource dataSource;

    @Autowired
    private MybatisAppInterceptor mybatisAppInterceptor;

    @Bean
    public FastMybatisPageDao getFastMybatisPageDao() {
        FastMybatisPageDao fastMybatisPageDao = new FastMybatisPageDao();
        fastMybatisPageDao.setInitPageSize(15);
        fastMybatisPageDao.setSqlSessionTemplate(sqlSessionTemplate);

        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setPlugins(new MybatisAppInterceptor[]{mybatisAppInterceptor});
        log.info("fastMybatisPageDao================="+fastMybatisPageDao);
        log.info("sqlSessionTemplate================="+sqlSessionTemplate);
        log.info("dataSource=========="+dataSource);
        log.info(dataSource.getDriverClassName());
        log.info(dataSource.getUsername());
        log.info(dataSource.getUrl());
        return fastMybatisPageDao;
    }


}
