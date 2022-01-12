package com.iamofoe.queue.domain.dao;

import com.iamofoe.queue.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
