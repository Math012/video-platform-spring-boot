package br.com.math012.service;

import br.com.math012.VO.AccountVO;
import br.com.math012.config.security.JwtTokenProvider;
import br.com.math012.exceptions.ExistingObjectException;
import br.com.math012.models.UserModel;
import br.com.math012.repository.UserRepository;
import br.com.math012.utils.encoder.EncodePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.math012.VO.*;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity singin(AccountVO account){
        try {
            var username = account.getUsername();
            var password = account.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            var user = userRepository.findByUsername(username);
            var token = new tokenVO();
            if (user != null){
                // Adicionar as roles
                token = jwtTokenProvider.createToken(username, user.getRoles());
            }else {
                throw new UsernameNotFoundException("this "+username+" not found!");
            }
            return ResponseEntity.ok(token);
        }catch (Exception e){
            throw new BadCredentialsException("Invalid account with credentials: " + account.getUsername());
        }
    }

    public void registerUser(RegisterVO register){
        register.setPassword(EncodePassword.enconde(register.getPassword()));
        if (userRepository.findByUsername(register.getUsername())!= null){
            throw new ExistingObjectException("this username: "+ register.getUsername()+", already registered");
        }
        var user = new UserModel(register.getUsername()
                ,register.getFullname()
                ,register.getPassword()
                ,true
                ,true
                ,true
                ,true);
        userRepository.save(user);
    }
}
