package com.itheima.config;


import com.itheima.interceptor.TestOfInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class TestOfInterceptorConfig implements WebMvcConfigurer {

   // @Autowired
    private TestOfInterceptor testOfInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testOfInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
