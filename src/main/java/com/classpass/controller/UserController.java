package com.classpass.controller;

import com.classpass.dao.UserDao;
import com.classpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserDao userDto) {
        return userService.saveUser(userDto);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserDao loginDto) {
        return userService.loginUser(loginDto);
    }
}
