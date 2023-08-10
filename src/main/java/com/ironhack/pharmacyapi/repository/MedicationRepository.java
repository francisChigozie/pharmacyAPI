package com.ironhack.pharmacyapi.repository;

import com.ironhack.pharmacyapi.model.Medication;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
    Optional<Medication> findById(long id);
    List<Medication> findAllByName(String name);

    Optional<Object> findById(SingularAttribute<AbstractPersistable, Serializable> id);
}
