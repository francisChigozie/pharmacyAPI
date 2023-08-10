package com.ironhack.pharmacyapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private UUID id;
    private List<MedicationDto> medications;
    private String customerName;
    private BigDecimal totalPrice;
    private ZonedDateTime orderDate;

}
