package org.nabiha.mobileapi.features.likes.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nabiha.mobileapi.features.products.dtos.ProductResponseDTO;
import org.nabiha.mobileapi.features.users.dtos.UsersResponseDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikesResponseDTO {
    private Long id;
    private ProductResponseDTO product;
    private UsersResponseDTO user;
}
