package com.hearien.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DruidConfiguration
 * @Author WangHaiyang
 * @Date 2018/12/6 17:12
 **/
@Configuration
public class DruidConfiguration {
    /**
     * 开启druid监控
     * @return
     */


    @Bean
    public ServletRegistrationBean druidStatView() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setName("DruidStatView");
        StatViewServlet servlet = new StatViewServlet();
        registrationBean.setServlet(servlet);
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "admin");
        registrationBean.setInitParameters(initParameters);
        Collection<String> urlMappings = new ArrayList<>();
        urlMappings.add("/druid/*");
        registrationBean.setUrlMappings(urlMappings);
        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
         filterRegistrationBean.addUrlPatterns("/*");
         filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
         return filterRegistrationBean;
    }


     @Bean
     @ConfigurationProperties(prefix="spring.datasource")
     public DataSource druidDataSource() {
        return new DruidDataSource();
     }


}
