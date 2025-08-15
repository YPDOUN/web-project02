package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtUtils {

    // 设置密钥
    private static final String SECRET_KEY = "aXRoZWltYQ==";

    // 设置过期时间为12小时(单位：毫秒)
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000;

    /**
     * 生成JWT令牌
     * @param claims 自定义载荷数据
     * @return JWT令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 设置加密算法和密钥
                .addClaims(claims) // 添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌字符串
     * @return Claims 载荷数据
     */
    public static Claims parseJwt(String jwt) throws Exception {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 设置密钥用于解析
                .parseClaimsJws(jwt) // 解析JWT字符串
                .getBody(); // 获取载荷部分
    }
}
