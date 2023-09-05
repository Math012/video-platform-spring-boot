package br.com.math012.controller;

import br.com.math012.models.UserModel;
import br.com.math012.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> findAll(){
        return userService.findAll();
    }
}
