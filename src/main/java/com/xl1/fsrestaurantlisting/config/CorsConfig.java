package com.xl1.fsrestaurantlisting.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/restaurant/**") // Configure the URL pattern you want to allow
            .allowedOrigins("http://localhost:4200") // Add allowed origins
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Add allowed HTTP methods
            .allowCredentials(true); // Enable cookies, if needed
    }
}
