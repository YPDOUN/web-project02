package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter(urlPatterns = "/*") // 拦截所有请求
@Slf4j
public class DemoFilter implements Filter {
    //初始化方法，在服务器启动时执行一次，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init ....");
    }

    //过滤方法，每次请求时执行一次，多次请求时执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //放行请求
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //销毁方法，在服务器关闭时执行一次，只执行一次
    @Override
    public void destroy() {
        log.info("destroy ....");
    }
}
