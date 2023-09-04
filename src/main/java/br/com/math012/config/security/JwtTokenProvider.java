package br.com.math012.config.security;

import br.com.math012.exceptions.InvalidTokenJwtException;
import br.com.math012.utils.securitymanager.ManagerSecurity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import br.com.math012.VO.tokenVO;

import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {


    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:18000000}")
    private long validateMiliSeconds = 18000000;


    @Autowired(required = false)
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct void init(){
        secretKey = ManagerSecurity.encryptSecretKey(secretKey);
        algorithm = ManagerSecurity.getAlgorithm(secretKey);
    }

    public tokenVO createToken(String username, List<String> roles){
        Date date = new Date();
        Date validation = new Date(date.getTime() + validateMiliSeconds);
        var token = getToken(username, roles, date, validation);
        return new tokenVO(username,true, date,token);
    }

    private String getToken(String username, List<String> roles, Date date, Date validation) {
        String url = ManagerSecurity.getUrl();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(date)
                .withExpiresAt(validation)
                .withSubject(username)
                .withIssuer(url)
                .sign(algorithm).strip();
    }

    public Authentication authentication(String token){
        DecodedJWT decodedJWT = ManagerSecurity.decodedJWT(token, secretKey);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null&& bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public Boolean validateToken(String token){
        DecodedJWT decodedJWT = ManagerSecurity.decodedJWT(token, secretKey);
        try {
            if (decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }
            return true;
        }catch (Exception e){
            throw new InvalidTokenJwtException("Expired or invalid JWT TOKEN!");
        }
    }
}