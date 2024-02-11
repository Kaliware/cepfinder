package br.com.kaliware.service.cepfinder.cepfinder.controller.cep.exception;

import br.com.kaliware.service.cepfinder.cepfinder.service.exception.api.ApiSocketException;
import br.com.kaliware.service.cepfinder.cepfinder.service.exception.api.ApiTimeoutException;
import br.com.kaliware.service.cepfinder.cepfinder.service.exception.resource.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Collections;

import static java.util.Objects.nonNull;


@ControllerAdvice
@Tag(name = "Manipulador de Exceções", description = "Manipulador de exceções para tratamento de erros na API")
public class ControllerExceptionHandler {

  @ExceptionHandler(ApiTimeoutException.class)
  @ApiResponse(responseCode = "504", description = "Tempo limite de conexão excedido",
     content = @Content(
        mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = StandardError.class))
  )
  public ResponseEntity<StandardError> handleApiTimeout(ApiTimeoutException e, HttpServletRequest request) {
    return buildResponse(HttpStatus.GATEWAY_TIMEOUT, e.getMessage(), request);
  }

  @ExceptionHandler(ApiSocketException.class)
  @ApiResponse(responseCode = "503", description = "Erro de comunicação com o servidor",
     content = @Content(
        mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = StandardError.class))
  )
  public ResponseEntity<StandardError> handleApiSocket(ApiSocketException e, HttpServletRequest request) {
    return buildResponse(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), request);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
     content = @Content(
        mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = StandardError.class))
  )
  public ResponseEntity<StandardError> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    return buildResponse(HttpStatus.NOT_FOUND, e.getMessage(), request);
  }

  private ResponseEntity<StandardError> buildResponse(HttpStatus status, String message, HttpServletRequest request) {
    StandardError error = new StandardError(
       Instant.now(),
       status.value(),
       Collections.singletonList(message != null ? message : status.getReasonPhrase()),
       request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }

}