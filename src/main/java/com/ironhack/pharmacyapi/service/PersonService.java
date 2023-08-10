package com.ironhack.pharmacyapi.service;

import com.ironhack.pharmacyapi.constants.MedicationConstants;
import com.ironhack.pharmacyapi.model.Person;
import com.ironhack.pharmacyapi.model.Roles;
import com.ironhack.pharmacyapi.repository.PersonRepository;
import com.ironhack.pharmacyapi.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(MedicationConstants.CUSTOMER_ROLE);
        person.setRoles(role);
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}
