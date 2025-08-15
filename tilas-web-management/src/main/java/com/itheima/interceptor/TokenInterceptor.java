package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //可在注册拦截器的类中配置
        /*//1.获取请求路径
        String requestPath = request.getRequestURI();
        log.info("请求路径：{}", requestPath);

        //2.判断请求路径是否为登录请求
        if (requestPath.contains("/login")) {
            log.info("请求登录，放行");
            return true;
        }*/

        //3.不是登录请求，先获取请求头里的token
        String token = request.getHeader("token");
        log.info("请求头token：{}", token);

        //4.判断请求头是否有token，无则返回401
        if (token == null || token.isEmpty()) {
            log.info("token无效，返回401状态码");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5.校验token是否有效，无效返回401
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            log.info("令牌无效");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException(e);
        }

        log.info("令牌有效，放行");
        return true;
    }
}
