package com.example.spring_example.config;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.dsig.keyinfo.PGPData;

@Configuration
public class DbConfig {
    @Bean
    public DataSource dataSource(
            @Value("${database.url}") String url,
            @Value("${database.user}")String user,
            @Value("${database.password}")String password
    ) {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(url);
        ds.setPassword(password);
        ds.setUser(user);

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
