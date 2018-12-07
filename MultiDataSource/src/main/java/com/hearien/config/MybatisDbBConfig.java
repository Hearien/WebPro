package com.hearien.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @ClassName MybatisDbBConfig
 * @Author WangHaiyang
 * @Date 2018/12/6 15:41
 **/
@Configuration
@MapperScan(basePackages = {"com.hearien.dao.db2"}, sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisDbBConfig {
    public static final String MAPPER_LOCATION = "classpath:mybatis/db2/*.xml";

    @Autowired
    @Qualifier("secondDataSource")
    private DataSource ds2;

    @Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds2);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(MybatisDbBConfig.MAPPER_LOCATION);
        factoryBean.setMapperLocations(resource);
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2());
        return template;
    }
}
