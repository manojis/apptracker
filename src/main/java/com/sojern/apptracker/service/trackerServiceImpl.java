package com.sojern.apptracker.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    
    public byte[] getImage() throws IOException {
    	
    	Resource resource = new ClassPathResource("/one.gif");
    	InputStream input = resource.getInputStream();
    	byte[] output = StreamUtils.copyToByteArray(input);
    	return output;
    }
}
