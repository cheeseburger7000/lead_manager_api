package com.example.lead_manager_api.service;

import com.example.lead_manager_api.model.Lead;
import com.example.lead_manager_api.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;

    @Transactional
    public void deleteByIdAndUserId(Long leadId, Long userId) {
        leadRepository.deleteByIdAndUserId(leadId, userId);
    }

    public List<Lead> findAllByUserId(Long userId) {
        return leadRepository.findAllByUserId(userId);
    }

    @Transactional
    public Lead save(Lead lead) {
        return leadRepository.save(lead);
    }
}
