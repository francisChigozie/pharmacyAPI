package com.ironhack.pharmacyapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "pharmacy_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    private List<Medication> medications;

    @Column(nullable = false)
    private String customerName;


    @Column(nullable = false)
    private BigDecimal totalPrice;

    @CreationTimestamp
    private ZonedDateTime orderDate;

}
