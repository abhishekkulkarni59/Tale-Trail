package com.code.taletrail.controller;

import com.code.taletrail.exception.ResourceNotFoundException;
import com.code.taletrail.payload.UserDto;
import com.code.taletrail.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //GET
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId){
        UserDto user = null;
        try {
            user = userService.getUserById(userId);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(Map.of("message", "User Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //POST
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //PUT
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto updatedUserDto = null;
        try{
            updatedUserDto = userService.updateUser(userDto, userId);
        }
        catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(Map.of("message", "User Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        try{
            userService.deleteUser(userId);
        }
        catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(Map.of("message", "User Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Map.of("message", "User Deleted Successfully"), HttpStatus.OK);
    }

}
