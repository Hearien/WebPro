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
 * 数据源A
 * @ClassName MybatisDbAConfig
 * @Author WangHaiyang
 * @Date 2018/12/6 15:41
 **/
@Configuration
@MapperScan(basePackages = {"com.hearien.dao.db1"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisDbAConfig {

    public static final String MAPPER_LOCATION = "classpath:mybatis/db1/*.xml";

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource ds1;


    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds1); // 使用主数据源, 连接主库
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(MybatisDbAConfig.MAPPER_LOCATION);
        factoryBean.setMapperLocations(resource);
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory1());
    }
}
