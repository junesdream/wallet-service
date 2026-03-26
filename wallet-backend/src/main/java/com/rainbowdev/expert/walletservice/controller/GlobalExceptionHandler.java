package com.rainbowdev.expert.walletservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice // Diese Klasse "beobachtet" alle Controller auf Fehler
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class) // Wenn eine RuntimeException geworfen wird...
    public ResponseEntity<Object> handleRuntime(RuntimeException e) {
        return ResponseEntity
                .status(400) // ...sende Status 400 (Bad Request)
                .body(Map.of("error", e.getMessage())); // ...und diese JSON-Nachricht
    }
}