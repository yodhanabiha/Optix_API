package org.nabiha.mobileapi.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UsersRequestDTO {

    @NotBlank(message = "product email shouldn't be NULL OR EMPTY")
    private String email;
    @NotBlank(message = "product password shouldn't be NULL OR EMPTY")
    private String password;
    @NotBlank(message = "product name shouldn't be NULL OR EMPTY")
    private String name;
    @NotBlank(message = "product phone shouldn't be NULL OR EMPTY")
    private String phone;

}
