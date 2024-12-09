package com.code.taletrail.controller;

import com.code.taletrail.payload.UserDto;
import com.code.taletrail.security.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/")
    public String login(@RequestBody UserDto userDto){
        return loginService.verify(userDto);
    }

}
