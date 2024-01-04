package com.fase4FIAP.streaming.application.exceptions.dto;

import org.springframework.http.HttpStatus;

public record ErrorDetails(String message, HttpStatus status) {
}
