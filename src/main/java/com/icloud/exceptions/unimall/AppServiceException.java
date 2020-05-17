package com.icloud.exceptions.unimall;

/**
 */
public class AppServiceException extends ServiceException {

    public AppServiceException(ServiceExceptionDefinition definition) {
        super(definition);
    }

    public AppServiceException(String message, int code) {
        super(message,code);
    }
}
