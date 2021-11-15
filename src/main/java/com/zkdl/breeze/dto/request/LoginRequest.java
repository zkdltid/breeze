package com.zkdl.breeze.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Username cant not be null")
    private String username;

    @NotBlank(message = "Password cant not be null")
    private String password;
}