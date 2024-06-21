package org.nabiha.mobileapi.features.histories.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HistoriesRequestDTO {
    @NotNull(message = "price item shouldn't be NULL OR EMPTY")
    private int price_item;
    @NotNull(message = "total item shouldn't be NULL OR EMPTY")
    private int total_item;
    @NotNull(message = "total price shouldn't be NULL OR EMPTY")
    private int total_price;
    @NotBlank(message = "shipping shouldn't be NULL OR EMPTY")
    private String shipping;
    @NotBlank(message = "address shouldn't be NULL OR EMPTY")
    private String address;
    @NotNull(message = "product_id shouldn't be NULL")
    private Long product_id;
    @NotNull(message = "user_id shouldn't be NULL")
    private Long user_id;
}
