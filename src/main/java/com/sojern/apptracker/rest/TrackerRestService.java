package com.sojern.apptracker.rest;

import com.sojern.apptracker.exception.ImageNotFoundException;
import com.sojern.apptracker.service.trackerService;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class TrackerRestService {

    private static final Logger logger = LoggerFactory.getLogger(TrackerRestService.class);

    @Autowired
    trackerService trackerService;

    /**
     * Endpoint ping and get the status of the file
     */
    @Scope("request")
    @RequestMapping(value = "ping", method = RequestMethod.GET, 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFileStatus (
    		@RequestParam(value="path",required = false) String filePath, 
    		@RequestParam(value="name",required = false) String fileName) {
        
            String response = "";
            HttpStatus httpStatus;

            String uri = "/" + filePath + "/" + fileName;
            boolean fileStatus = trackerService.getFileStatus(uri);
            logger.info("Status of the requested file: " + fileStatus); 
            
            if(fileStatus){
                httpStatus = HttpStatus.OK;
                response = "OK";
            } else {
            	// Better, We can handle using ExceptionHandler for custom business logging
            	// mentioned in the getImage() next method
                httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
            }
            
            return new ResponseEntity<String>(String.valueOf(response), httpStatus);     
    } 
    
    /**
     * Endpoint img for getting the image from Resource folder
     */
    @Scope("request")
    @RequestMapping(value = "img", method = RequestMethod.GET, 
    produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity getImage () throws IOException {
    	
    	int size=0;
    	byte[] response;
    	ByteArrayResource resource = null;
    	    	

        response = trackerService.getImage();
        size = response.length;
        resource = new ByteArrayResource(response);

		logger.info("Valid gif file obtained");
    	
    	return ResponseEntity.ok()
    			.contentLength(size)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
