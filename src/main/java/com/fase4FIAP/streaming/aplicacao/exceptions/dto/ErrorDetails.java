package com.fase4FIAP.streaming.aplicacao.exceptions.dto;

import org.springframework.http.HttpStatus;

public record ErrorDetails(String message, HttpStatus status) {
}
