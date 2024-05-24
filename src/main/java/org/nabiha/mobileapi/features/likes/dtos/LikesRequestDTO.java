package org.nabiha.mobileapi.features.likes.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LikesRequestDTO {
    @NotNull(message = "product_id shouldn't be NULL")
    private Long product_id;
    @NotNull(message = "user_id shouldn't be NULL")
    private Long user_id;
}
