package org.nabiha.mobileapi.features.histories;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nabiha.mobileapi.features.products.ProductsEntity;
import org.nabiha.mobileapi.features.users.UsersEntity;

import java.time.LocalDateTime;
@Entity
@Table(name = "histories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int price_item;
    @Column(nullable = false)
    private int total_item;

    @Column(nullable = false)
    private int total_price;

    @Column(nullable = false)
    private LocalDateTime purchase_date;

    @Column(nullable = false)
    private String shipping;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductsEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UsersEntity user;

    @Column
    private LocalDateTime at_created;

    @Column
    private LocalDateTime at_updated;
}
