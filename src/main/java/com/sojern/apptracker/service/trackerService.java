package com.sojern.apptracker.service;

import java.io.IOException;

/**
 * @author Manoj
 */

public interface trackerService {

    public boolean getFileStatus(String uri);
    public byte[] getImage() throws IOException;
    
}
