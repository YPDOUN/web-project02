package com.itheima.interceptor;


import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//@Component
@Slf4j
public class TestOfInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //config中已设置放行了/login

        //判断请求头token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.info("请求头无token或token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //判断是否合法
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("令牌不合法");
            throw new RuntimeException(e);
        }

        //令牌合法，放行
        log.info("令牌合法：{}", token);
        return true;
    }
}
