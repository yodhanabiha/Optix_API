package org.nabiha.mobileapi.features.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductsRequestDTO {
    @NotBlank(message = "product title shouldn't be NULL OR EMPTY")
    private String title;

    private String description;

    private String spec;

    @NotNull(message = "product price shouldn't be NULL")
    private int price;
}
