package org.nabiha.mobileapi.features.products.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nabiha.mobileapi.features.likes.dtos.LikesResponseDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String spec;
    private String imageurl;
    private int price;
    private List<LikesResponseDTO> likes;
}
