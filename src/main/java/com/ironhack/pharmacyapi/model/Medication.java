package com.ironhack.pharmacyapi.model;

import com.ironhack.pharmacyapi.dto.MedicationDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor @Entity
public class Medication extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Medication must be empty")
    private String name;

    @NotBlank(message = "Description of Medication needed")
    private String description;

    @NotBlank(message = "Price must not be empty")
    private String price;

    @NotBlank(message = "Manufacturer must be empty ")
    private String manufacturer;

}
