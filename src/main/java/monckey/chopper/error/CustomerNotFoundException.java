package monckey.chopper.error;

import lombok.Getter;

@Getter
public class CustomerNotFoundException extends RuntimeException{

    private String error;
    private String message;

    public CustomerNotFoundException(ErrorCode code){
        super(code.getMessage());
        this.error = ErrorCode.CUSTOMER_NOT_FOUND.getErrorCode();
        this.message = ErrorCode.CUSTOMER_NOT_FOUND.getMessage();
    }

    public CustomerNotFoundException(final String message){
        super(message);
        this.error = ErrorCode.CUSTOMER_NOT_FOUND.getErrorCode();
        this.message = ErrorCode.CUSTOMER_NOT_FOUND.getMessage();
    }
}
