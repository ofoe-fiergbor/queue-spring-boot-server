package com.iamofoe.queue.service.impl;

import com.iamofoe.queue.domain.dto.AddRoleToUserDto;
import com.iamofoe.queue.domain.dto.LoginSuccessResponse;
import com.iamofoe.queue.domain.dto.RegisterUserDto;
import com.iamofoe.queue.domain.dto.UserLoginDto;
import com.iamofoe.queue.domain.model.Role;
import com.iamofoe.queue.domain.model.User;
import com.iamofoe.queue.service.interfaces.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    @Override
    public String createAndSaveRole(Role role) {
        return null;
    }

    @Override
    public void addRoleToUser(AddRoleToUserDto addRoleToUserDto) {

    }

    @Override
    public User registerNewUser(RegisterUserDto registerUserDto) {
        return null;
    }

    @Override
    public LoginSuccessResponse authenticateUser(UserLoginDto userLoginDto) {
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }


}
