package org.nabiha.mobileapi.features.users.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nabiha.mobileapi.enums.UserRole;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersResponseDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String imageurl;
    private UserRole role;
    private LocalDateTime at_created;
    private LocalDateTime at_updated;
}
