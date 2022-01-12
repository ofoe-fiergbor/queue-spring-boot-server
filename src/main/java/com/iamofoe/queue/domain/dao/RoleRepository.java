package com.iamofoe.queue.domain.dao;

import com.iamofoe.queue.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(String name);

}
