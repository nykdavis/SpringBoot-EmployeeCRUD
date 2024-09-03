package com.piersqure.boot.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Handle specific exception
    @ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request){
		Map<String,Object> response = new HashMap<>();
		response.put("timestamp",LocalDateTime.now());
		response.put("message", ex.getMessage());
		response.put("details", request.getDescription(false));
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
    
    // Handle specific exception
    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", ex.getMessage());
        response.put("details", request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
	
	// Handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", ex.getMessage());
        response.put("details", request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
