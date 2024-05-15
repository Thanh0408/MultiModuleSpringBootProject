package demo.com.vn.dto.exeption;

import demo.com.vn.enums.EnumBaseError;

public class AppException extends RuntimeException{

    public AppException(EnumBaseError errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private final EnumBaseError errorCode;
}
