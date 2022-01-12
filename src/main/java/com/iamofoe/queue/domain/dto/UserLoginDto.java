package com.iamofoe.queue.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {
    private String phoneNumber;
    private String password;
}
