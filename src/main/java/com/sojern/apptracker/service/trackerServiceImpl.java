package com.sojern.apptracker.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sojern.apptracker.exception.ImageNotFoundException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
public class trackerServiceImpl implements trackerService {
    public boolean getFileStatus(String uri){

        boolean status = false;
        Path path = Paths.get(uri);
        if (Files.exists(path) && Files.isRegularFile(path)){
            status = true;
        }

        return status;
    }
    
    public byte[] getImage() {
    	
    	Resource resource = new ClassPathResource("/one.gif");
        byte[] output;
        try{
            InputStream input = resource.getInputStream();
            output = StreamUtils.copyToByteArray(input);
        } catch (IOException e) {
            throw new ImageNotFoundException(e);
        }
    	return output;
    }
}
