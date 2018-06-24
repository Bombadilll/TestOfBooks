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

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testBook";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "bombadil";

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
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(mysqlJdbcUrl());
        dataSource.setUsername(mysqlLogin());
        dataSource.setPassword(mysqlPassword());
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionsManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    private String mysqlJdbcUrl() {
        String env = System.getenv(JDBC_URL);
        return env != null ? env : "jdbc:mysql://localhost:3306/testBook";
    }

    private String mysqlLogin() {
        String env = System.getenv(LOGIN);
        return env != null ? env : "root";
    }

    private String mysqlPassword() {
        String env = System.getenv(PASSWORD);
        return env != null ? env : "bombadil";
    }

}