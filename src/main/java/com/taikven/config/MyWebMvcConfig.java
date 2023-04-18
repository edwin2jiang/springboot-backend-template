package com.taikven.config;

/**
 * 
 * @Date: 2023/4/11
 * @Version: 1.0
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .maxAge(1800)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("*");
    }
}
