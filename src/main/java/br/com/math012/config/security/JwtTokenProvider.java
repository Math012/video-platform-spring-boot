package br.com.math012.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider {


    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:18000000}")
    private long validateMiliSeconds = 18000000;

    @Autowired
    private UserDetailsService userDetailsService;


}
