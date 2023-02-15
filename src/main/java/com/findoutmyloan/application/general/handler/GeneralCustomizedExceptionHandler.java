package com.findoutmyloan.application.general.handler;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.dto.RestResponse;
import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.general.exception.ItemNotFoundException;
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
public class GeneralCustomizedExceptionHandler extends ResponseEntityExceptionHandler {
   private final String validationMessage = "Validation failed!";
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        GeneralExceptionResponse genExceptionResponse = new GeneralExceptionResponse(errorDate, message, description);

        RestResponse<GeneralExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
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

        GeneralExceptionResponse genExceptionResponse = new GeneralExceptionResponse(errorDate, message, description);

        RestResponse<GeneralExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage(message);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllGenBusinessException(GeneralBusinessException ex, WebRequest webRequest){

        //Exc casting
        return getObjectResponseEntity(ex.getBaseErrorMessage(), (ItemNotFoundException) ex);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllIllegalFieldException(IllegalFieldException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = ex.getBaseErrorMessage().getDetailMessage();

        GeneralExceptionResponse genExceptionResponse = new GeneralExceptionResponse(errorDate, message, description);

        RestResponse<GeneralExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage(message);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Date errorDate = new Date();
        String description = ex.getBindingResult().toString();

        GeneralExceptionResponse genExceptionResponse = new GeneralExceptionResponse(errorDate, validationMessage, description);
        RestResponse<GeneralExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessage(validationMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }
}
