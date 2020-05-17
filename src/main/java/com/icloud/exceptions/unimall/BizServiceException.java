package com.icloud.exceptions.unimall;

/**
 */
public class BizServiceException extends ServiceException {

    public BizServiceException(ServiceExceptionDefinition definition) {
        super(definition);
    }

    public BizServiceException(String message, int code) {
        super(message,code);
    }
}
