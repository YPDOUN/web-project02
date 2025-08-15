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
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestPath = request.getRequestURI();

        //2.判断请求路径是否为登录请求
        if (requestPath.contains("login")) {
            log.info("请求登录，放行");
            filterChain.doFilter(request, response);
            return;
        }

        //3.如果不是登录请求，则校验token，先获取请求头里的token
        String token = request.getHeader("token");

        //4.判断是否有token以及是否为空，无则返回401
        if (token == null || token.isEmpty()) {
            log.info("令牌无效或者令牌为空");
            //设置401状态吗
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5.有token，则校验是否有效，无效返回401
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("令牌无效");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            throw new RuntimeException(e);

        }

        //6.登录成功，放行
        log.info("令牌有效，放行");
        filterChain.doFilter(request, response);
    }
}
