package com.fase4FIAP.streaming.application.exceptions;

import com.fase4FIAP.streaming.application.controller.UserController;
import com.fase4FIAP.streaming.application.exceptions.dto.ErrorDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class GlobalExceptionHandlerTest {

  @Test
  void generateExceptionHandleNotFoundException(){
    var exception =  mock(NotFoundException.class);

    when(exception.getMessage()).thenReturn("Usuário não encontrado");

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    ResponseEntity<ErrorDetails> actualResponse = globalExceptionHandler.handleNotFoundException(exception);

    ResponseEntity<ErrorDetails> expectedResponse = new ResponseEntity<>(
        new ErrorDetails("Usuário não encontrado", HttpStatus.NOT_FOUND),
        HttpStatus.NOT_FOUND);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  void generateExceptionHandleValidationExceptions(){
    MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
    BindingResult bindingResult = mock(BindingResult.class);

    when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
    when(bindingResult.getAllErrors()).thenReturn(List.of(
        new ObjectError("fieldName", "Error message 1"),
        new ObjectError("fieldName", "Error message 2")
    ));

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    ResponseEntity<ErrorDetails> actualResponse = globalExceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

    ResponseEntity<ErrorDetails> expectedResponse = new ResponseEntity<>(
        new ErrorDetails("Error message 1; Error message 2; ", HttpStatus.BAD_REQUEST),
        HttpStatus.BAD_REQUEST);

    assertEquals(expectedResponse, actualResponse);
  }

  @Test
  void generateExceptionHandleAlreadyFavoritedException(){
    var exception = mock(AlreadyFavoritedException.class);
    when(exception.getMessage()).thenReturn("Error message");

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    ResponseEntity<ErrorDetails> actualResponse = globalExceptionHandler.handleAlreadyFavoritedException(exception);

    ResponseEntity<ErrorDetails> expectedResponse = new ResponseEntity<>(
        new ErrorDetails("Error message", HttpStatus.BAD_REQUEST),
        HttpStatus.BAD_REQUEST);

    assertEquals(expectedResponse, actualResponse);
  }




}
