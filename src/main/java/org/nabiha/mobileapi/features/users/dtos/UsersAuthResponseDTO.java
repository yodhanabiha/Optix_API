package org.nabiha.mobileapi.features.users.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nabiha.mobileapi.dtos.JwtDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersAuthResponseDTO {
    UsersResponseDTO Users;
    JwtDto token;

}
