package com.code.taletrail.security;

import com.code.taletrail.exception.ApiException;
import com.code.taletrail.payload.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    public String verify(UserDto userDto){
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new ApiException(e.getMessage());
        }
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userDto.getEmail());
        }
        return "Authentication Failed";
    }

}
