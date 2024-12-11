package com.code.taletrail.controller;

import com.code.taletrail.payload.UserDto;
import com.code.taletrail.security.LoginService;
import com.code.taletrail.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto){
        return loginService.verify(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto){
        UserDto registeredUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(registeredUserDto, HttpStatus.CREATED);
    }

}
