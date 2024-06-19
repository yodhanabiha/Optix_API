package org.nabiha.mobileapi.features.histories;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

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
    private String Address;
}
