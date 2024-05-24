package org.nabiha.mobileapi.features.users.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
    @NotBlank(message = "email shouldn't be NULL OR EMPTY")
    public String email;
    @NotBlank(message = "password shouldn't be NULL OR EMPTY")
    public String password;

}
