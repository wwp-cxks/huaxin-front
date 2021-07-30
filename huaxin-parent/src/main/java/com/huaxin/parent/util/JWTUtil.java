//package com.huaxin.parent.util;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTCreator;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import java.util.Calendar;
//import java.util.Map;
//
//public class JWTUtil {
//
//    private static final String SING = "!@#fdaf#%^h^&(*";
//
//    /**
//     * create Token   header.payload.signature
//     */
//    public static String getToken(Map<String, String> map) {
//
//        Calendar instance = Calendar.getInstance();
//        instance.add(Calendar.DATE, 7); // default out of date in 7 days
//
//        // create jwt builder
//        JWTCreator.Builder builder = JWT.create();
//
//        // payload
//        map.forEach((k, v) -> { // lambda
//            builder.withClaim(k, v);
//        });
//        String token = builder.withExpiresAt(instance.getTime()) // Token deadline
//                .sign(Algorithm.HMAC256(SING)); // sign
//        return token;
//    }
//
//    /**
//     * verify if Token is right
//     */
//    public static DecodedJWT verify(String token) {
//        // if fail,throw Exception
//        // else return
//        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
//    }
//
//    public static String verifyStringParam(DecodedJWT vertify, String key) {
//        return vertify.getClaim(key).asString();
//    }
//}
