package com.ironhack.pharmacyapi.service;

import com.ironhack.pharmacyapi.constants.MedicationConstants;
import com.ironhack.pharmacyapi.model.Contact;
import com.ironhack.pharmacyapi.model.Medication;
import com.ironhack.pharmacyapi.repository.ContactRepository;
import com.ironhack.pharmacyapi.repository.MedicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service @Slf4j  @ApplicationScope //@SessionScope //@RequestScope
public class MedServiceMVC {
    @Autowired
    private MedicationRepository medicationRepository;

    private int counter = 0;

    public MedServiceMVC(){
        System.out.println("Medication Service Bean initialized");
    }

    public boolean saveMedicationDetails(Medication medication){
        boolean isSaved = false;
        medication.setName(medication.getName());
        medication.setDescription(medication.getDescription());
        medication.setManufacturer(medication.getManufacturer());
        medication.setCreatedAt(LocalDate.now().atStartOfDay());
        Medication savedMedication = medicationRepository.save(medication);
        if(null != savedMedication && savedMedication.getId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Medication> findAllMedications(){
        return medicationRepository.findAllByName(MedicationConstants.OPEN);
    }

    public boolean updateMsgMedication(long id){
        boolean isUpdated = false;
        Optional<Medication> medication = medicationRepository.findById(id);
        medication.ifPresent( medication1 -> {
            medication1.setUpdatedAt(medication.get().getUpdatedAt());
        });
        Medication updatedMedication = medicationRepository.save(medication.get());
        if(null != updatedMedication && updatedMedication.getId()!=null) {
            isUpdated = true;
        }
        return isUpdated;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
