package com.icloud.exceptions.unimall;

/**
 * 第三方接口服务异常
 */
public class ThirdPartServiceException extends ServiceException {

    public ThirdPartServiceException(ServiceExceptionDefinition definition) {
        super(definition);
    }

    public ThirdPartServiceException(String message, int code) {
        super(message, code);
    }

}
