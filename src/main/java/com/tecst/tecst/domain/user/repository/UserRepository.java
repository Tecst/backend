package com.tecst.tecst.domain.user.repository;

import com.tecst.tecst.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserId(UUID id);

    User findByEmail(String email);

}
