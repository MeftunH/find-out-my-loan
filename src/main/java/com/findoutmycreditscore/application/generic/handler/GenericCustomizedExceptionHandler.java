package com.findoutmycreditscore.application.generic.handler;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.generic.dto.RestResponse;
import com.findoutmycreditscore.application.generic.errorMessage.BaseErrorMessage;
import com.findoutmycreditscore.application.generic.exception.GenericBusinessException;
import com.findoutmycreditscore.application.generic.exception.IllegalFieldException;
import com.findoutmycreditscore.application.generic.exception.ItemNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class GenericCustomizedExceptionHandler extends ResponseEntityExceptionHandler {
   private final String validationMessage = "Validation failed!";
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        GenericExceptionResponse genExceptionResponse = new GenericExceptionResponse(errorDate, message, description);

        RestResponse<GenericExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage(message);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllItemNotFoundException(ItemNotFoundException ex, WebRequest webRequest){

        return getObjectResponseEntity(ex.getBaseErrorMessage(), ex);
    }

    private ResponseEntity<Object> getObjectResponseEntity(BaseErrorMessage baseErrorMessage, ItemNotFoundException ex) {
        Date errorDate = new Date();
        String message = baseErrorMessage.getMessage();
        String description = baseErrorMessage.getDetailMessage();

        GenericExceptionResponse genExceptionResponse = new GenericExceptionResponse(errorDate, message, description);

        RestResponse<GenericExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage(message);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllGenBusinessException(GenericBusinessException ex, WebRequest webRequest){

        //Exc casting
        return getObjectResponseEntity(ex.getBaseErrorMessage(), (ItemNotFoundException) ex);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllIllegalFieldException(IllegalFieldException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = ex.getBaseErrorMessage().getDetailMessage();

        GenericExceptionResponse genExceptionResponse = new GenericExceptionResponse(errorDate, message, description);

        RestResponse<GenericExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage(message);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Date errorDate = new Date();
        String description = ex.getBindingResult().toString();

        GenericExceptionResponse genExceptionResponse = new GenericExceptionResponse(errorDate, validationMessage, description);
        RestResponse<GenericExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage(validationMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }
}
