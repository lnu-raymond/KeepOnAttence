package com.leevan.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.Utils
 * @className:      JwtUtil
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/25 14:25
 * @version:    1.0
 */ 

public class JwtUtil {


    public static final String SECRET = "LEEVAN-FONG";
    public static final long EXPIRE_TIME= 60 * 60 * 1000;

    public static String getToken(Integer userId,String userName)
    {
        HashMap<String,Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Date expiresAt = new Date(System.currentTimeMillis()+EXPIRE_TIME);
      return  JWT.create()
                .withHeader(header)
                .withClaim("userId",userId)
                .withClaim("userName",userName)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }


    public static void verify(String token) {
        try{
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        }catch (TokenExpiredException tokenExpiredException)
        {
            throw tokenExpiredException;
        }
    }


    public static DecodedJWT decodeToken(String token)
    {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify;
    }





}