package br.com.math012.controller;

import br.com.math012.VO.AccountVO;
import br.com.math012.VO.RegisterVO;
import br.com.math012.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
@Tag(name = "Endpoint authentication")
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Authentication a user with JWT TOKEN")
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AccountVO account){
        if (account == null
                ||account.getUsername()==null
                ||account.getUsername().isBlank()
                ||account.getPassword().isBlank())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid user");
        var token = authService.singin(account);
        if (token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid token");
        }
        return token;
    }

    @Operation(summary = "Register a new User")
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterVO account){
        authService.registerUser(account);
        URI location = URI.create(String.format("/api/auth/register/%s",account.getUsername()));
        return ResponseEntity.created(location).body("User: " + account.getUsername()+", registered!");
    }
}
