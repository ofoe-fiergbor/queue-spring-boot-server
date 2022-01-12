package com.iamofoe.queue.service.impl;

import com.iamofoe.queue.domain.dao.RoleRepository;
import com.iamofoe.queue.domain.dao.UserRepository;
import com.iamofoe.queue.domain.dto.AddRoleToUserDto;
import com.iamofoe.queue.domain.dto.LoginSuccessResponse;
import com.iamofoe.queue.domain.dto.RegisterUserDto;
import com.iamofoe.queue.domain.dto.UserLoginDto;
import com.iamofoe.queue.domain.model.Role;
import com.iamofoe.queue.domain.model.User;
import com.iamofoe.queue.service.interfaces.UserService;
import com.iamofoe.queue.utils.exceptions.UserAlreadyExistException;
import com.iamofoe.queue.utils.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final static String ROLE_USER = "ROLE_USER";

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getPassword(), user.getPassword(), authorities);
    }
    @Override
    public Role createAndSaveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(AddRoleToUserDto addRoleToUserDto) {
        User user = userRepository.findUserByPhoneNumber(addRoleToUserDto.getPhoneNumber())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        Role role = roleRepository.findRoleByName(addRoleToUserDto.getRoleName())
                .orElseThrow(() -> new UsernameNotFoundException("Role not found"));
        user.getRoles().add(role);
    }

    @Override
    public User registerNewUser(RegisterUserDto registerUserDto) throws UserAlreadyExistException {
        if (userRepository.existsUserByPhoneNumber(registerUserDto.getPhoneNumber())) {
            throw new UserAlreadyExistException("User with phone number already exist.");
        }
        String password = passwordEncoder.encode(registerUserDto.getPassword());
        User user = new User(registerUserDto.getFirstName(), registerUserDto.getLastName(),
                registerUserDto.getPhoneNumber(), password );
        user.getRoles().add(roleRepository.findRoleByName(ROLE_USER).orElseThrow(()-> new UsernameNotFoundException("User not found!")));
        return userRepository.save(user);
    }

    @Override
    public LoginSuccessResponse authenticateUser(UserLoginDto userLoginDto) {
        User user = userRepository.findUserByPhoneNumber(userLoginDto.getPhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDto.getPhoneNumber(), userLoginDto.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);
        String token = jwtUtils.createAccessToken(userLoginDto.getPhoneNumber());


        return new LoginSuccessResponse(user.getId(),
                user.getFirstName(), user.getLastName(), user.getPhoneNumber(), token);
    }

    @Override
    public boolean logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return true;
    }


}
