package com.ironhack.pharmacyapi.controller;

import com.ironhack.pharmacyapi.model.Contact;
import com.ironhack.pharmacyapi.model.Medication;
import com.ironhack.pharmacyapi.service.ContactService;
import com.ironhack.pharmacyapi.service.MedServiceMVC;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller @RequestMapping("med")
public class MedicationController {

    private final MedServiceMVC medServiceMVC;

    @Autowired
    public MedicationController(MedServiceMVC medServiceMVC) {
        this.medServiceMVC = medServiceMVC;
    }

    @RequestMapping(value = "/medication",method = { RequestMethod.GET})
    public String displayMedicationPage(Model model) {
        model.addAttribute("medication", new Medication());
        return "createmed.html";
    }

    @RequestMapping(value = "/saveInfo",method = POST)
    public String saveInformation(@Valid @ModelAttribute("medication") Medication medication, Errors errors) {
        if(errors.hasErrors()){
            log.error("Medication form validation failed due to : " + errors.toString());
            return "createmed.html";
        }
        medServiceMVC.saveMedicationDetails(medication);
        return "redirect:/medication";
    }

    @RequestMapping("/displayInformation")
    public ModelAndView displayMedications(Model model) {
        List<Medication> medicationInfo = medServiceMVC.findAllMedications();
        ModelAndView modelAndView = new ModelAndView("medication.html");
        modelAndView.addObject("medicationInfo",medicationInfo);
        return modelAndView;
    }

    @RequestMapping(value = "/closeInfo",method = GET)
    public String closeMsg(@RequestParam int id) {
        medServiceMVC.updateMsgMedication(id);
        return "redirect:/displayInformation";
    }

}

