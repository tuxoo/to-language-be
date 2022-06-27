package com.tolanguage.tolanguage.config;

import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
public class PostgresConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource myDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate postgresJdbcTemplate(DataSource dataSource, JdbcProperties jdbcProperties) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        JdbcProperties.Template template = jdbcProperties.getTemplate();
        jdbcTemplate.setFetchSize(template.getFetchSize());
        jdbcTemplate.setMaxRows(template.getMaxRows());
        if (template.getQueryTimeout() != null) {
            jdbcTemplate.setQueryTimeout((int) template.getQueryTimeout().getSeconds());
        }

        return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterPostgresJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
