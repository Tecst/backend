package com.tecst.tecst.domain.user.mapper;

import com.tecst.tecst.domain.user.dto.request.CreateUserRequestDto;
import com.tecst.tecst.domain.user.dto.response.CreateUserResponseDto;
import com.tecst.tecst.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public CreateUserResponseDto toDto(User user) {return CreateUserResponseDto.builder().userId(user.getUserId()).email(user.getEmail()).build();}

    public User toEntity(CreateUserRequestDto dto) {
        User user = User.builder()
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .role("USER")
                        .build();
        return user;
    }


//    public List<UserDto.RegisterResponse> toDtoList(List<User> list) {
//        return list.stream().map(this::toDto).collect(Collectors.toList());
//    }
}