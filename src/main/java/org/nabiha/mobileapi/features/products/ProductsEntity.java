package org.nabiha.mobileapi.features.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String spec;

    @Column(nullable = false)
    private String imageurl;

    @Column(nullable = false)
    private int price;

    @Column
    private LocalDateTime at_created;

    @Column
    private LocalDateTime at_updated;
}
