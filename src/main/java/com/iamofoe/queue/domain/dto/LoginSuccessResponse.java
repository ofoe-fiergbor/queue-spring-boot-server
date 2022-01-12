package com.iamofoe.queue.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginSuccessResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String token;

}
