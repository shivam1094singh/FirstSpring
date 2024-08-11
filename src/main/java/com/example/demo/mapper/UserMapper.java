package com.example.demo.mapper;

import com.example.demo.entities.UserEntity;
import com.example.demo.payloads.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity userEntity);
}
