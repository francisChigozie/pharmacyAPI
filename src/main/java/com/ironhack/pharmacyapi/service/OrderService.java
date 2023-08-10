package com.ironhack.pharmacyapi.service;

import com.ironhack.pharmacyapi.dto.OrderDto;
import com.ironhack.pharmacyapi.mappers.OrderMapper;
import com.ironhack.pharmacyapi.model.Medication;
import com.ironhack.pharmacyapi.model.Order;
import com.ironhack.pharmacyapi.repository.MedicationRepository;
import com.ironhack.pharmacyapi.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MedicationRepository medicationRepository;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {

        List<Medication> medications = orderDto.getMedications().stream()
                .map(medication -> (Medication) medicationRepository.findById(id)
                        .orElseThrow(
                                () -> new IllegalArgumentException(
                                        "Medication with id " + id +
                                                " does not exist")))
                .toList();
        Order order = orderMapper.toEntity(orderDto);
        order.setMedications(medications);

        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }
}