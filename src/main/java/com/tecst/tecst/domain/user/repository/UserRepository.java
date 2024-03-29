package com.tecst.tecst.domain.user.repository;

import com.tecst.tecst.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long id);

    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

}
