package net.javaguides.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            //two stars for all path or directory
            .allowedOriginPatterns("*")
            //all patterns
            .allowedMethods("*")
            //all methods
            .maxAge(3600);
        //how long 
    }
}
