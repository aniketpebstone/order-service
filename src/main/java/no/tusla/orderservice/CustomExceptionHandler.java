package no.tusla.orderservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
   
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("~ MethodArgumentNotValidException ~ {}", ex);
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
                
        ErrorResponse errorDTO = new ErrorResponse("Validation failed!",details, new Date());
        return errorDTO;
    }
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ResourceAccessException.class)
    public ErrorResponse handleResourceAccessException(ResourceAccessException ex) {
        log.error("~ ResourceAccessException ~ {}", ex);
        ErrorResponse errorDTO = new ErrorResponse("Supply service is down!",null, new Date());
        return errorDTO;
    }
	
	
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        log.error("~ Generic Exception ~ {}", ex);
        ErrorResponse errorDTO = new ErrorResponse(ex.getMessage(),null, new Date());
        return errorDTO;
    }
    
}
