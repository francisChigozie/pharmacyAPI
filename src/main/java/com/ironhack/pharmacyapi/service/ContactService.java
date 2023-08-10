package com.ironhack.pharmacyapi.service;

import com.ironhack.pharmacyapi.constants.ContactConstnts;
import com.ironhack.pharmacyapi.constants.MedicationConstants;
import com.ironhack.pharmacyapi.model.Contact;
import com.ironhack.pharmacyapi.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service @Slf4j  @ApplicationScope //@SessionScope //@RequestScope
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    private int counter = 0;

    public ContactService(){
        System.out.println("Contact Service Bean initialized");
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(ContactConstnts.OPEN);
        contact.setCreatedAt(LocalDateTime.now());
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findByStatus(MedicationConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(long contactId){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(MedicationConstants.CLOSE);
            contact1.setUpdatedAt(contact.get().getUpdatedAt());
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getContactId()!=null) {
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
