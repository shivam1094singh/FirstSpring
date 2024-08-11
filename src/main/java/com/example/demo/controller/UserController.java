package com.example.demo.controller;

import com.example.demo.entities.UserEntity;
import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.UserDto;
import com.example.demo.service.UserServiceImp.UserServiceImp;
import com.example.demo.service.Userservice;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.payloads.*;

import java.rmi.server.UID;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private Userservice  userService;

    //create -post
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }
    //put-update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")Integer userId){
        UserDto updatedUser=this.userService.updateUser(userDto, Integer.toUnsignedLong(userId));
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteEntity(@Valid @PathVariable("userId")Long userId){

            userService.deleteUser(userId);
            return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);
    }
    //Get-User
    @GetMapping("/")
    public  ResponseEntity<List<UserDto>> getAllUser()
    {
        return ResponseEntity.ok(this.userService.getAllUser());

    }


    //GetSingleUser
    @GetMapping("/{userId}")
    public  ResponseEntity<UserDto> getSingleUser(@PathVariable("userId")Long userId)
    {
        return ResponseEntity.ok(this.userService.getUserById(userId));

    }

}
