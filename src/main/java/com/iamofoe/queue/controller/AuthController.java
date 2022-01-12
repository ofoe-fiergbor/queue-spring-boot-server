package com.iamofoe.queue.controller;

import com.iamofoe.queue.domain.dto.RegisterUserDto;
import com.iamofoe.queue.domain.dto.UserLoginDto;
import com.iamofoe.queue.service.interfaces.UserService;
import com.iamofoe.queue.utils.exceptions.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody RegisterUserDto registerUserDto) {
        try {
            return new ResponseEntity<>(userService.registerNewUser(registerUserDto), HttpStatus.CREATED);
        } catch (UserAlreadyExistException exception) {
            return new ResponseEntity<>("Error: "+ exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginDto userLoginDto) {
        try {
            return new ResponseEntity<>(userService.authenticateUser(userLoginDto), HttpStatus.OK);
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return new ResponseEntity<>("Error: "+usernameNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>(userService.logout(), HttpStatus.OK);
    }

}
