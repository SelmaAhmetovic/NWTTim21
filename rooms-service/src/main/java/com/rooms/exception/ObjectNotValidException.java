package com.rooms.exception;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception handler for invalid objects
 * @author Adijata
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ObjectNotValidException  {
	
    private String error;
    private Object fieldValue;

    /**
     * Create error message from fieldErrors
     * @param fieldErrors
     */
    public ObjectNotValidException(Errors fieldErrors) {
    	List<FieldError> errors = fieldErrors.getFieldErrors();
    	
    	error = "";
    	for(FieldError er : errors){
    		error = String.format("%s\n%s field is not valid. Message:%s", error, er.getField(), er.getDefaultMessage());
    	}
    	
    	errors.toString();
    }

    public String getError() {
        return error;
    }
    
    /**
     * Override to string method to return error message
     */
    public String toString() { 
        return error;
    } 
}
