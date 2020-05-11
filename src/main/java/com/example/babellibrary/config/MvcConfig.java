package com.example.babellibrary.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error_403").setViewName("springSecurity/error_403");
    }
    
}