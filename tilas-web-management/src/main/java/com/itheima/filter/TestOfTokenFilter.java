package com.itheima.filter;


import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class TestOfTokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.判断路径中是否有/login
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/login")) {
            //放行
            log.info("登录请求，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //2.若非登录请求，获取请求里的token
        String token = request.getHeader("token");

        //3.判断token是否存在
        if (token == null || token.isEmpty()) {
            log.info("token不存在");
            //设置401状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //4.如果存在，判断是否合法
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            //不合法，设置401状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException(e);
        }

        //5.最后验证成功
        log.info("令牌有效,放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
