package com.sojern.apptracker.exception;

/**
 * @author Manoj
 */

public class ImageNotFoundException extends trackerServiceException{
	
	private static final long serialVersionUID = 1L;

    public static final int STATUS=204;
    public static final String MESSAGE = "No images found for this search";
    public static final String CODE = "image.not.found";


    /**
     * Construct a ImageNotFoundException with default CODE, MESSAGE and STATUS
     */
    public ImageNotFoundException()
    {
        super(CODE, MESSAGE, STATUS, null);
    }
    public ImageNotFoundException(String message)
    {
        super(CODE, MESSAGE, STATUS, null);
    }
    /**
     * Construct a ImageNotFoundException with default CODE, MESSAGE, STATUS and the exception passed
     */
    public ImageNotFoundException(Throwable cause)
    {
        super(CODE, MESSAGE, STATUS, cause);
    }


}
