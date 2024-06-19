package org.nabiha.mobileapi.features.users.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.nabiha.mobileapi.enums.UserRole;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UsersRequestDTO {
    @NotBlank(message = "user email shouldn't be NULL OR EMPTY")
    private String email;
    @NotBlank(message = "user password shouldn't be NULL OR EMPTY")
    private String password;
    @NotBlank(message = "user name shouldn't be NULL OR EMPTY")
    private String name;
    @NotBlank(message = "user phone shouldn't be NULL OR EMPTY")
    private String phone;
    private String imageurl;
    private LocalDateTime date_birth;
    private String gender;
    private UserRole role;

}
