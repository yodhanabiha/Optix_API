package org.nabiha.mobileapi.features.carts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;
import org.nabiha.mobileapi.features.users.UsersEntity;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartsResponseDTO {
    private Long id;
    private ProductsResponseDTO product;
    private Long userId;
    private int total;
    private Boolean selected;
    private LocalDateTime at_created;
    private LocalDateTime at_updated;
}
