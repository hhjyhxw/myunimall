package com.icloud.exceptions.unimall;

/**
 */
public class AdminServiceException extends ServiceException {

    public AdminServiceException(ServiceExceptionDefinition definition) {
        super(definition);
    }

    public AdminServiceException(String message, int code) {
        super(message,code);
    }

}
