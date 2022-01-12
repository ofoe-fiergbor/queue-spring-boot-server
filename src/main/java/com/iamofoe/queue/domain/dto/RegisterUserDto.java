package com.iamofoe.queue.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}
