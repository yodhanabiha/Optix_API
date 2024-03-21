package org.nabiha.mobileapi.features.users.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
    @NotBlank(message = "product email shouldn't be NULL OR EMPTY")
    public String email;
    @NotBlank(message = "product password shouldn't be NULL OR EMPTY")
    public String password;

}
