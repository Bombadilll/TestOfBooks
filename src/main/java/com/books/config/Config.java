package com.books.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;


@Configuration
@EnableWebMvc
@ComponentScan("com.books.*")
@Import({ SecurCfg.class })
@EnableTransactionManagement
@EnableScheduling
public class Config extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl(oracleJdbcUrl());
        dataSource.setUsername(oracleLogin());
        dataSource.setPassword(oraclePassword());
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionsManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    private String oracleJdbcUrl() {
        String env = System.getenv("ORACLE_JDBC_URL");
        return env != null ? env : "jdbc:oracle:thin:@//localhost:1521/XE";
    }

    private String oracleLogin() {
        String env = System.getenv("ORACLE_LOGIN");
        return env != null ? env : "books";
    }

    private String oraclePassword() {
        String env = System.getenv("ORACLE_PASSWORD");
        return env != null ? env : "books";
    }

}