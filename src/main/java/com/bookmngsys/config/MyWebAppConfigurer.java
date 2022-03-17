package com.bookmngsys.config;

import com.bookmngsys.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Newway
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Bean
    LoginInterceptor localInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor()).addPathPatterns("/api/**")
                .excludePathPatterns("/api/book/getBooks", "/api/user/login", "/api/user/register","/api/book/getBookDetail",
                        "/swagger-resources/**", "/webjars/**",
                        "/swagger-ui/**", "/v2/api-docs");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/");
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
//                "classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations(
//                "classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/static/img/**").addResourceLocations(
//                "classpath:/META-INF/resources/img/");
    }
}