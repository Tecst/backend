package com.tecst.tecst.domain.user.service;

import com.tecst.tecst.domain.user.dto.request.CreateUserRequestDto;
import com.tecst.tecst.domain.user.dto.response.CreateUserResponseDto;
import com.tecst.tecst.domain.user.entity.User;
import com.tecst.tecst.domain.user.mapper.UserMapper;
import com.tecst.tecst.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void register(CreateUserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
    }

    public User findUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(null);
    }

}
