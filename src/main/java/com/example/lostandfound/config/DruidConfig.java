package com.example.lostandfound.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ilpvc
 * @Date: 2022/9/29 09:15
 * @Description:
 */
@EnableTransactionManagement
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() throws SQLException {
        return new DruidDataSource();
    }


    //配置 Druid 监控管理后台的Servlet；
    //内置 Servlet 容器时没有web.xml文件，所以使用 Spring Boot 的注册 Servlet 方式
//    @Bean
//    public ServletRegistrationBean a(){
//        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//
//        Map<String, String> initParameters = new HashMap<>();
//        initParameters.put("loginUsername","root");
//        initParameters.put("loginPassword","12346");
//
//        bean.setInitParameters(initParameters);
//        return  bean;
//    }

}