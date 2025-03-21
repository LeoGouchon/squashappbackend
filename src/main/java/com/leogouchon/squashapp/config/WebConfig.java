package com.leogouchon.squashapp.config;

import com.leogouchon.squashapp.security.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/users")
                .addPathPatterns(String.valueOf(HttpMethod.GET), "/api/users")
                .addPathPatterns(String.valueOf(HttpMethod.PUT), "/api/users")
                .addPathPatterns(String.valueOf(HttpMethod.DELETE), "/api/users")
                .excludePathPatterns("/api/authenticate/login");
    }
}
