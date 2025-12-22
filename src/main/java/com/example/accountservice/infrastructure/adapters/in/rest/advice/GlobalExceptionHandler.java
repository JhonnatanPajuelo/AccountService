package com.example.accountservice.infrastructure.adapters.in.rest.advice;

import com.example.accountservice.domain.model.exception.BusinessException;
import com.example.accountservice.domain.model.exception.InfrastructureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Captura errores de negocio (Ej: Saldo insuficiente, montos inválidos)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        log.warn("Regla de negocio violada: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("BUSINESS_ERROR", ex.getMessage()));
    }

    // Captura errores técnicos (Ej: Base de datos caída, error de serialización)
    @ExceptionHandler(InfrastructureException.class)
    public ResponseEntity<ErrorResponse> handleInfrastructure(InfrastructureException ex) {
        log.error("Error técnico detectado: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("TECHNICAL_ERROR", "Lo sentimos, el servicio no está disponible temporalmente."));
    }

    // Captura errores de validación de los Records (@NotNull, @DecimalMin)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("VALIDATION_ERROR", message));
    }

    public record ErrorResponse(String code, String message) {}
}
