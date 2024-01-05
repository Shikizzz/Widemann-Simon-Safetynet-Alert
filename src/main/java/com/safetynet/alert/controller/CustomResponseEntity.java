package com.safetynet.alert.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomResponseEntity {

    public ResponseEntity FileNotFoundResponseEntity() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("ErrorCause", "DataBase file not found. Please report the problem to SafetyNet admins")
                .body("DataBase file not found. Please report the problem to SafetyNet admins");
    }

    public ResponseEntity<String> FileNotFoundEditResponseEntity() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("ErrorCause", "DataBase file not found. Please report the problem to SafetyNet admins")
                .body("DataBase file not found. Please report the problem to SafetyNet admins");
    }
}
