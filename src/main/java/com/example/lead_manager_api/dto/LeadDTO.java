package com.example.lead_manager_api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LeadDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private String message;
}
