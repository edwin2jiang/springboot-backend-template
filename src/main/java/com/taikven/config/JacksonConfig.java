package com.taikven.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @Date: 2023/4/13
 * @Version: 1.0
 */
@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return new Jackson2ObjectMapperBuilder()
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai"))
                .dateFormat(dateFormat)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .failOnUnknownProperties(false);
    }

}
