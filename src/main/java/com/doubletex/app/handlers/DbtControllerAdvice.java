package com.doubletex.app.handlers;
import com.doubletex.app.errors.DbtBadRequest;
import com.doubletex.app.errors.DbtNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DbtControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DbtBadRequest handlerValidationsException(MethodArgumentNotValidException ex){
        DbtBadRequest  dbtBadRequest = new DbtBadRequest();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message =  error.getDefaultMessage();
            dbtBadRequest.addValidations(field, message);
        });
        return dbtBadRequest;
    }
    @ExceptionHandler(DbtNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DbtNotFound handleCustomException(DbtNotFound ex) {
        return ex;
    }

    @ExceptionHandler(DbtBadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DbtBadRequest handleCustomException(DbtBadRequest ex) {return ex; }
}