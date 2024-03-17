package org.nabiha.mobileapi.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime at_created;
    private LocalDateTime at_updated;
}
