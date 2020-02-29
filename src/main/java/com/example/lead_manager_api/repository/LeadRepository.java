package com.example.lead_manager_api.repository;

import com.example.lead_manager_api.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    void deleteByIdAndUserId(Long id, Long userId);
    List<Lead> findAllByUserId(Long userId);
}
