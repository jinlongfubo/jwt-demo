package com.tanghuan.jwt.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by tanghuan on 2017/3/8.
 */


@Configuration
@EnableJpaRepositories(basePackages = {"com.tanghuan.jwt.repository"})
public class JpaConfig {

    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("org.gjt.mm.mysql.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/jwt?createDatabaseIfNotExist=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
        ds.setUrl(url);
        ds.setUsername("root");
        ds.setPassword("root");

        // 配置初始化大小，最大，最小
        ds.setInitialSize(10);
        ds.setMinIdle(10);
        ds.setMaxActive(50);

        // 配置获取连接等待超时时间
        ds.setMaxWait(60000);

        // 配置间隔多久进行一次检测，检测需要关闭的空闲连接 单位是毫秒
        ds.setTimeBetweenEvictionRunsMillis(60000);

        // 一个连接在连接池的最小生存时间，单位是毫秒
        ds.setMinEvictableIdleTimeMillis(300000);

        ds.setValidationQuery("SELECT 'x'");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);

        // 打开PSCache,并且指定每个连接上PSCache的大小，如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false
        ds.setPoolPreparedStatements(false);
        ds.setMaxPoolPreparedStatementPerConnectionSize(20);

        // 配置监控统计拦截器的filters
        try {
            ds.setFilters("wall,stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(false);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        HashMap<String, Object> propetys = new HashMap<>();
        propetys.put("hibernate.format_sql", "true");
        lemfb.setJpaPropertyMap(propetys);
        lemfb.setDataSource(dataSource());
        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lemfb.setPackagesToScan("com.tanghuan.jwt.entity.domain");
        return lemfb;
    }



}
