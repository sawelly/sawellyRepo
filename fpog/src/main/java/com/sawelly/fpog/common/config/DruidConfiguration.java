//https://blog.csdn.net/blueheart20/article/details/52384032
package com.sawelly.fpog.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.sawelly.fpog.common.page.FastMybatisPageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
public class DruidConfiguration {
    private Logger log = LoggerFactory.getLogger(DruidConfiguration.class);
    private final Environment environment;

    @Autowired
    public DruidConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Primary
    public DruidDataSource dataSource() throws Exception {


        DruidDataSource dataSource = new DruidDataSource();

        //驱动类
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));

        //基本属性 url、username、password
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
//
//        log.info("dataSource=========="+dataSource);
//        log.info("driver-class-name=========="+environment.getProperty("spring.datasource.driver-class-name"));
//        log.info("url=========="+environment.getProperty("spring.datasource.url"));
//        log.info("username=========="+environment.getProperty("spring.datasource.username"));
//        log.info("password=========="+environment.getProperty("spring.datasource.password"));
//        //连接池初始化大小、最小和最大值
//        dataSource.setInitialSize(environment.getProperty("initialSize", Integer.class));
//        dataSource.setMinIdle(environment.getProperty("minIdle", Integer.class));
//        dataSource.setMaxActive(environment.getProperty("maxActive", Integer.class));
//
//        //获取连接等待超时的时间
//        dataSource.setMaxWait(environment.getProperty("maxWait", Long.class));
//        //间隔多久才进行一次检测，检测需要关闭的空闲连接
//        dataSource.setTimeBetweenEvictionRunsMillis(environment.getProperty("timeBetweenEvictionRunsMillis", Long.class));
//        //连接在池中最小生存的时间
//        dataSource.setMinEvictableIdleTimeMillis(environment.getProperty("minEvictableIdleTimeMillis", Long.class));
//
//        String validationQuery = environment.getProperty("validationQuery");
//        if (validationQuery != null && !"".equals(validationQuery)) {
//            dataSource.setValidationQuery(validationQuery);
//        }
//        dataSource.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("testWhileIdle")));
//        dataSource.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("testOnBorrow")));
//        dataSource.setTestOnReturn(Boolean.parseBoolean(environment.getProperty("testOnReturn")));
//
//        boolean opened = Boolean.parseBoolean(environment.getProperty("poolPreparedStatements"));
//        if (opened) {
//            //打开PSCache，并且指定每个连接上PSCache的大小
//            dataSource.setMaxPoolPreparedStatementPerConnectionSize(environment.getProperty("maxPoolPreparedStatementPerConnectionSize", Integer.class));
//        }
//
//        //监控统计拦截的filters
//        dataSource.setFilters(environment.getProperty("filters"));
//
//        //通过connectProperties属性来打开mergeSql功能，慢SQL记录
//        String connectionPropertiesStr = environment.getProperty("connectionProperties");
//        if (connectionPropertiesStr != null && !"".equals(connectionPropertiesStr)) {
//            Properties connectProperties = new Properties();
//            String[] propertiesList = connectionPropertiesStr.split(";");
//            for (String propertiesTmp : propertiesList) {
//                String[] obj = propertiesTmp.split("=");
//                String key = obj[0];
//                String value = obj[1];
//                connectProperties.put(key, value);
//            }
//            dataSource.setConnectProperties(connectProperties);
//        }
//        //合并多个DruidDataSource的监控数据
//        dataSource.setUseGlobalDataSourceStat(Boolean.parseBoolean(environment.getProperty("useGlobalDataSourceStat")));
        return dataSource;


    }
}
