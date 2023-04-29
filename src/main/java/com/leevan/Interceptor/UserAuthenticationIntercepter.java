package com.leevan.Interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.leevan.Utils.JwtUtil;
import com.leevan.Utils.RedisUtil;
import com.leevan.pojo.ControllerRes;
import com.leevan.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.Interceptor
 * @className:      UserAuthenticationIntercepter
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/25 17:33
 * @version:    1.0
 */ 

public class UserAuthenticationIntercepter implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private userService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");


        if(token==null)
        {
            System.out.println("用户未登录");
            return false;
        }

        System.out.println("prehandler方法被调用了");

        if(token!=null)
        {

            //token过期 捕捉到异常就续签
            try{
                DecodedJWT decodedJWT = JwtUtil.decodeToken(token);
                Integer userId =decodedJWT.getClaim("userId").asInt();
                String userName = decodedJWT.getClaim("userName").asString();
                // 计算当前时间是否超过过期时间的一半，如果是就帮用户续签 --------------------------
                // 此处并不是永久续签，只是为 大于过期时间的一半 且 小于过期时间 的 token 续签
                Long expTime = decodedJWT.getExpiresAt().getTime();
                System.out.println("这里被调用了"+decodedJWT.getIssuedAt().getTime());
                Long iatTime = decodedJWT.getIssuedAt().getTime();

                Long nowTime = new Date().getTime();
                if((nowTime-iatTime) > (expTime-iatTime)/2) {
                    // 生成新的jwt
                    String newJwt = JwtUtil.getToken(userId,userName);
                    // 加入返回头
                    response.addHeader("refresh-token", newJwt);
                }
            }
            catch (TokenExpiredException tokenExpiredException)
            {
//                这里以后调用日志框架输入异常
                tokenExpiredException.printStackTrace();
                System.out.println("token过期");
                addResBody(response,new ControllerRes(-1,"令牌过期"));
                return false;

            }catch (Exception exception)
            {
//                这里以后调用日志框架输入异常
                exception.printStackTrace();
//                如果是其他异常说明token不合法
                addResBody(response,new ControllerRes(-1,"令牌不合法"));
                return  false;
            }
        }
        return  true;

    }

    private void addResBody(HttpServletResponse response, ControllerRes res) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); //设置状态码

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.write(gson.toJson(res));
        out.flush();
        out.close();

    }



}