package com.miguel.stockmanager.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.miguel.stockmanager.exceptions.IdNotFoundException;
import com.miguel.stockmanager.exceptions.NameNotFoundException;
import com.miguel.stockmanager.exceptions.QuantityAboveLimitException;
import com.miguel.stockmanager.responses.StandardErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler({ NameNotFoundException.class, IdNotFoundException.class })
  public ResponseEntity<StandardErrorResponse> handleNotFoundException(RuntimeException ex,
      HttpServletRequest request) {
    String error = "Resource not found.";
    HttpStatus status = HttpStatus.NOT_FOUND;
    var standardError = createErrorResponse(status, error, ex.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(standardError);
  }

  @ExceptionHandler(QuantityAboveLimitException.class)
  public ResponseEntity<StandardErrorResponse> handleQuantityAboveLimitException(QuantityAboveLimitException ex,
      HttpServletRequest request) {
    String error = "Quantity to remove exceeds available quantity in stock.";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var standardError = createErrorResponse(status, error, ex.getMessage(), request.getRequestURI());
    return ResponseEntity.status(status).body(standardError);
  }

  @ExceptionHandler({ TransactionSystemException.class, DataIntegrityViolationException.class,
      MethodArgumentNotValidException.class, HttpMessageNotReadableException.class })
  public ResponseEntity<StandardErrorResponse> handleBadRequestException(Exception ex, HttpServletRequest request) {
    String error = "Invalid parameters for the operation.";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    var standardError = createErrorResponse(status, error, "Check the entered parameters.", request.getRequestURI());
    return ResponseEntity.status(status).body(standardError);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<StandardErrorResponse> handleUnknownException(Exception ex, HttpServletRequest request) {
    String error = "An unexpected error occurred.";
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    var standardError = createErrorResponse(status, error, "Please try again later.", request.getRequestURI());
    return ResponseEntity.status(status).body(standardError);
  }

  private StandardErrorResponse createErrorResponse(HttpStatus status, String error, String message,
      String requestUri) {
    return new StandardErrorResponse(status, error, message, requestUri);
  }
}
