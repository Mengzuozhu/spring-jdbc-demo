package com.example.springjdbcdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * @author zuozhu.meng
 * @since 2020/12/9
 **/
@Configuration
@EnableJdbcAuditing
@EnableJdbcRepositories(basePackages = "com.example.springjdbcdemo.infrastruction")
public class ApplicationConfig {
}
