package com.icloud.exceptions;

import lombok.Data;

@Data
public class MallServiceException extends RuntimeException {
    private String code;
    private String message;

    public MallServiceException() {
    }

    public MallServiceException(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public MallServiceException(String message) {
        this.message = message;
    }

    public MallServiceException(String message, String code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public MallServiceException(String message, Throwable cause, String code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public MallServiceException(Throwable cause, String code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public MallServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }
}
