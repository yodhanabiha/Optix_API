package org.nabiha.mobileapi.features.likes.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikesResponseDTO {
    private Long id;
    private Long product_id;
    private Long user_id;
}
