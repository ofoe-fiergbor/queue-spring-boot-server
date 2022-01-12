package com.iamofoe.queue.service.interfaces;

import com.iamofoe.queue.domain.dto.*;
import com.iamofoe.queue.domain.model.Role;
import com.iamofoe.queue.domain.model.User;
import com.iamofoe.queue.utils.exceptions.UserAlreadyExistException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Role createAndSaveRole(Role role);
    void addRoleToUser(AddRoleToUserDto addRoleToUserDto);
    User registerNewUser(RegisterUserDto registerUserDto) throws UserAlreadyExistException;
    LoginSuccessResponse authenticateUser(UserLoginDto userLoginDto);
    boolean logout();
}
