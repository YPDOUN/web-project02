package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateToken() {
        Map<String,Object> map = new HashMap<>();
        map.put("id","1");
        map.put("username","lihua");


        String jwt = Jwts.builder().
                signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==")//设置加密算法和秘钥(第一部分)
                .addClaims(map)//添加自定义数据(第二部分)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置过期时间
                .compact();

        System.out.println(jwt);
        //eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJ1c2VybmFtZSI6ImxpaHVhIiwiZXhwIjoxNzU1MTgzMjg1fQ.oYC6SSfkA22UzrG79Qa8roXIMkE7wWt_P3QY1WUANkQ
    }

    @Test
    public void testParseToken() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJ1c2VybmFtZSI6ImxpaHVhIiwiZXhwIjoxNzU1MTgzMjg1fQ.oYC6SSfkA22UzrG79Qa8roXIMkE7wWt_P3QY1WUANkQ";

        Claims body = Jwts.parser().setSigningKey("aXRoZWltYQ==")//解析需要设置秘钥
                .parseClaimsJws(jwt)//解析JWT字符串
                .getBody();//获取载荷部分(第二部分的自定义数据)

        System.out.println(body);
    }
}
