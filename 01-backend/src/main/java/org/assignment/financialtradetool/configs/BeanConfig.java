package org.assignment.financialtradetool.configs;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        return mapper;
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type",
                "Accept","Authorization","Origin, Accept", "X-Request-With",
                "Access-Control-Request-Method","Access-Request-Control-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type",
                "Accept","Authorization","Origin, Accept", "X-Request-With",
                "Access-Control-Request-Method","Access-Request-Control-Headers"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
