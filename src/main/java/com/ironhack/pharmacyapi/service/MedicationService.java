package com.ironhack.pharmacyapi.service;

import com.ironhack.pharmacyapi.constants.MedicationConstants;
import com.ironhack.pharmacyapi.dto.MedicationDto;
import com.ironhack.pharmacyapi.exception.ResourceNotFoundException;
import com.ironhack.pharmacyapi.mappers.MedicationMapper;
import com.ironhack.pharmacyapi.model.Medication;
import com.ironhack.pharmacyapi.repository.MedicationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service @RequiredArgsConstructor
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;
    private final MedicationMapper mapper;

    public Medication addNewMedication(Medication medication){
        return medicationRepository.save(medication);
    }
    public MedicationDto addMedication(MedicationDto medicationDto){
        Medication medication = mapper.toEntity(medicationDto);
        medicationRepository.save(medication);
        log.info("Medication created: " + medication);
        return mapper.toDto(medication);
    }


    public Optional<Medication> findById(long id){
        return  medicationRepository.findById(id);
    }

   public MedicationDto findById(Long id) {
        return mapper.toDto(medicationRepository.findById(id)
                .orElseThrow( ()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Medication with id " + id + " not found.")));
    }

    public List<Medication> findAllMedications(){
        return medicationRepository.findAllByName(MedicationConstants.OPEN);
    }
   public List<MedicationDto> findAll(){
        return medicationRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public ResponseEntity<Medication> updateMedication2(Long id, Medication medicationDetails)
            throws ResourceNotFoundException {

        Medication medication =
                medicationRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Medication not found on :: " + id));

        medication.setName(medicationDetails.getName());
        medication.setDescription(medicationDetails.getDescription());
        medication.setPrice(medicationDetails.getPrice());
        medication.setManufacturer(medicationDetails.getManufacturer());
        final Medication updatedMedication = medicationRepository.save(medication);
        log.info("Medication updated: " + updatedMedication);
        return ResponseEntity.ok(updatedMedication);
    }
    public MedicationDto updateMedication(Long id, MedicationDto medicationDto) {
        if(!medicationRepository.existsById(id)) {
            throw new EntityNotFoundException("No medication with id " + id);
        }
        Medication entity = mapper.toEntity(medicationDto);
        entity.setId(id); // no funny business with ids included in RequestBody
        medicationRepository.save(entity);
        log.info("Medication updated: " + entity);
        return mapper.toDto(entity);
    }

    public Map<String, Boolean> deleteMedication(Long id) throws Exception {
        Medication medication =
                medicationRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Medication not found :: " + id));

        medicationRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Medication Successfully Deleted", Boolean.TRUE);
        return response;
    }

}
