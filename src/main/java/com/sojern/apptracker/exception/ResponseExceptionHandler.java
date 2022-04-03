package com.sojern.apptracker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
//import java.io.IOException;

@ControllerAdvice
public class ResponseExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ResponseExceptionHandler.class);
	
	ErrorResponse errorResponse = null;
	
	@ExceptionHandler({ FileNotFoundException.class})
    @ResponseBody
    public ResponseEntity handleMissingFile(Throwable t) {

        errorResponse = new ErrorResponse();
        errorResponse.setCode("file.not.found");
        errorResponse.setMessage("Missing file from tmp folder");
        return errorResponse(errorResponse,t, HttpStatus.SERVICE_UNAVAILABLE);
    }
	
	@ExceptionHandler({ ImageNotFoundException.class})
    @ResponseBody
    public ResponseEntity handleMissingImage(Throwable t) {

        errorResponse = new ErrorResponse();
        errorResponse.setCode("image.not.found");
        errorResponse.setMessage("Missing image from resource");
        return errorResponse(errorResponse,t, HttpStatus.SERVICE_UNAVAILABLE);
    }
	
	/**
     * Catch all for any other exceptions...
     */
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResponseEntity<?> handleAnyException(Exception e) {
        errorResponse = new ErrorResponse();
        errorResponse.setCode("service.failure");
        errorResponse.setMessage(e.getMessage());
        return errorResponse(errorResponse,e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<ErrorResponse> errorResponse(ErrorResponse errorResp,
                                                          Throwable throwable, HttpStatus status) {
        if (null != errorResp) {
            log.error("error caught: " + errorResp.getMessage(), throwable);

            return response(errorResp, status);
        } else {
            log.error("unknown error caught in RESTController, {} "+ status);
            return response(null, status);
        }
    }

    protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
        log.debug("Responding with a status of {} "+ status);
        return new ResponseEntity<>(body, new HttpHeaders(), status);
    }
	
	

}
