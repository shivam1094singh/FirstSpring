package com.example.demo.service;

import com.example.demo.payloads.UserDto;

import java.util.List;

public interface Userservice {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Long userId);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUser();
    void deleteUser(Long userId);





}
