package org.nabiha.mobileapi.features.carts.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.nabiha.mobileapi.features.products.ProductsEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartsRequestDTO {

    @NotNull(message = "productId shouldn't be NULL")
    private Long productId;
    @NotNull(message = "userId shouldn't be NULL")
    private Long userId;
    @NotNull(message = "total cart shouldn't be NULL")
    private int total;
    @NotNull(message = "selected cart shouldn't be NULL")
    private Boolean selected;
}
