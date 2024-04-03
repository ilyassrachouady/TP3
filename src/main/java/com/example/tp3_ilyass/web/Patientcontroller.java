package com.example.tp3_ilyass.web;

import com.example.tp3_ilyass.entities.Patient;
import com.example.tp3_ilyass.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class Patientcontroller {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "3") int size,
                        @RequestParam(name = "kw", defaultValue = "") String kw){
        Page<Patient> patientPage = patientRepository.findByNameContains(kw,PageRequest.of(page,size));
        model.addAttribute("ListPatients", patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id){
        patientRepository.deleteById(id);
        return "redirect:/index";
    }
}
