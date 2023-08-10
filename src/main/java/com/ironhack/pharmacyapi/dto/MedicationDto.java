package com.ironhack.pharmacyapi.dto;

import com.ironhack.pharmacyapi.model.BaseEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @Min(0)
    private double price; // as this is a primitive data type, it will default to 0.0 if omitted
    @NotEmpty
    private String manufacturer;
}
