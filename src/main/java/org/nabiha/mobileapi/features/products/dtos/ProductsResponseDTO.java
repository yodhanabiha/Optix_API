package org.nabiha.mobileapi.features.products.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String imageurl;
    private int price;
}
