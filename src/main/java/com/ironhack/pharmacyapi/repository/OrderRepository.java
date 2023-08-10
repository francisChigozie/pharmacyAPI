package com.ironhack.pharmacyapi.repository;

import com.ironhack.pharmacyapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
