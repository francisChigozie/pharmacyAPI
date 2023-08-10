package com.ironhack.pharmacyapi.mappers;

import com.ironhack.pharmacyapi.dto.MedicationDto;
import com.ironhack.pharmacyapi.model.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING) // uses = {}
public interface MedicationMapper {
    Medication toEntity (MedicationDto dto);
    MedicationDto toDto (Medication entity);
}
