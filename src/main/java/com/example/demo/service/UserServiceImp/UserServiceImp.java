package com.example.demo.service.UserServiceImp;

import com.example.demo.entities.UserEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payloads.UserDto;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.Userservice;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImp implements Userservice {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAbout(userDto.getAbout());
        UserEntity save = userRepo.save(userEntity);
        userDto.setId(save.getId());
        return userDto;
    }

    public UserDto updateUser(UserDto userDto, Long userId) {
        UserEntity userEntity = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAbout(userDto.getAbout());
        userRepo.save(userEntity);
        return userDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
        UserEntity userEntity = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return toUserDto(userEntity);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> alluser = new ArrayList<>();
        List<UserEntity> userEntityList = userRepo.findAll();
        for (UserEntity userEntity : userEntityList) {
            UserDto userDto = toUserDto(userEntity);
            alluser.add(userDto);
        }
        return alluser;
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            userRepo.deleteById(userId);
        } catch (EntityNotFoundException e) {
            log.error("userId: {} not present in DB", userId);
        }

    }

    static UserDto toUserDto(UserEntity userEntity) {
        if (Objects.isNull(userEntity)) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userDto.getPassword());
        userDto.setAbout(userDto.getAbout());
        return userDto;
    }

    static UserEntity toDtoUser(UserDto UserDto) {
        if(Objects.isNull(UserDto)){
            return null;

        }
        UserEntity user = new UserEntity();
        user.setId(user.getId());
        user.setEmail(user.getEmail());
        user.setName(user.getName());
        user.setPassword(user.getPassword());
        user.setAbout(user.getAbout());
        return user;




    }


}


