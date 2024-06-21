package org.nabiha.mobileapi.features.histories.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nabiha.mobileapi.features.products.dtos.ProductsResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoriesResponseDTO {
    private Long id;
    private int price_item;
    private int total_item;
    private int total_price;
    private LocalDateTime purchase_date;
    private String shipping;
    private String address;
    private ProductsResponseDTO product;
    private Long user_id;
}
