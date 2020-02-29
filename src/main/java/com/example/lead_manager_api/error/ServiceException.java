package com.example.lead_manager_api.error;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
