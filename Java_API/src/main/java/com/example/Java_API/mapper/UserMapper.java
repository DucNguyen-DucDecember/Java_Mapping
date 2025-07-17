package com.example.Java_API.mapper;

import com.example.Java_API.dto.response.UserResponseDTO;
import com.example.Java_API.dto.resquest.UserRequestDTO;
import com.example.Java_API.enity.Profile;
import com.example.Java_API.enity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "user.profile.cccd", target = "cccd")
    UserResponseDTO toResponse(final User user);

    @Mapping(source = "request.cccd", target = "profile.cccd")
    User toModel(final UserRequestDTO request);
}
