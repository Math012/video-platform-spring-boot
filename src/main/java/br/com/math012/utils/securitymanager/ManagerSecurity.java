package br.com.math012.utils.securitymanager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Base64;

public class ManagerSecurity {

    public static String encryptSecretKey(String key){
        String keyEncrypted = Base64.getEncoder().encodeToString(key.getBytes());
        return keyEncrypted;
    }

    public static Algorithm getAlgorithm(String key){
        Algorithm algorithm = Algorithm.HMAC256(key.getBytes());
        return algorithm;
    }

    public static DecodedJWT decodedJWT(String token, String key){
        Algorithm algorithm =  Algorithm.HMAC256(key.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return  decodedJWT;
    }
}
