package com.kingour.jdbc.gpdb.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.kingour.**.pgsql.mybatis", sqlSessionFactoryRef = "pgsqlSessionFactory")
public class PgsqlMybatisSannerConfig {

    static Logger logger = LoggerFactory.getLogger(PgsqlMybatisSannerConfig.class);
    
    private final PathMatchingResourcePatternResolver patheResolver = new PathMatchingResourcePatternResolver();

    @Autowired
    private Environment env;

    @Bean(name = "pgsqlTransactionManager")
    public DataSourceTransactionManager transcationManager(@Qualifier("dataSource") DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean(name = "pgsqlSessionFactory")
    public SqlSessionFactory sessionFactory(@Qualifier("dataSource") DataSource ds) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ds);
        String key = "pgsql";
        sessionFactory.setTypeAliasesPackage(env.getProperty("mybatis." + key + ".typeAliasesPackage"));
        sessionFactory.setMapperLocations(patheResolver.getResources(env.getProperty("mybatis." + key
                + ".mapperLocations")));
        sessionFactory.setConfigLocation(patheResolver.getResource(env
                .getProperty("mybatis." + key + ".configLocation")));
        return sessionFactory.getObject();
    }

}
