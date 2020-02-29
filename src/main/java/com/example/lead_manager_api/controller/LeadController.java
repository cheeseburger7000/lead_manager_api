package com.example.lead_manager_api.controller;

import com.example.lead_manager_api.dto.LeadDTO;
import com.example.lead_manager_api.error.ServiceException;
import com.example.lead_manager_api.model.Lead;
import com.example.lead_manager_api.model.User;
import com.example.lead_manager_api.repository.LeadRepository;
import com.example.lead_manager_api.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("api/leads")
@RestController
public class LeadController {
    @Autowired
    private LeadService leadService;

//    @GetMapping("{id}")
//    public Lead getLead(@PathVariable("id") Long id) {
//        Lead result = leadRepository.findById(id).get();
//        return result;
//    }

    @GetMapping
    public List<Lead> getLeads(HttpServletRequest request) {
        Map userSession = (Map) request.getSession().getAttribute("user");
        if (userSession == null) {
            throw new ServiceException("未认证");
        }
        User user = (User) userSession.get("user");

        List<Lead> result = leadService.findAllByUserId(user.getId());
        return result;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLead(@PathVariable("id") Long leadId, HttpServletRequest request) {
        Map userSession = (Map) request.getSession().getAttribute("user");
        if (userSession == null) {
            throw new ServiceException("未认证");
        }
        User user = (User) userSession.get("user");

        leadService.deleteByIdAndUserId(leadId, user.getId());
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Lead saveLead(@RequestBody @Valid LeadDTO leadDTO, HttpServletRequest request) {
        Map userSession = (Map) request.getSession().getAttribute("user");
        if (userSession == null) {
            throw new ServiceException("未认证");
        }
        User user = (User) userSession.get("user");

        Lead lead = Lead.builder()
                .name(leadDTO.getName())
                .email(leadDTO.getEmail())
                .message(leadDTO.getMessage())
                .userId(user.getId())
                .build();
        Lead result = leadService.save(lead);
        return result;
    }
}
