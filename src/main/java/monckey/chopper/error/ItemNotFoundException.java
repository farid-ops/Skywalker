package monckey.chopper.error;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException{

    private final String error;
    private final String message;

    public ItemNotFoundException(ErrorCode error){
        super(error.getMessage());
        this.error = ErrorCode.CUSTOMER_NOT_FOUND.getErrorCode();
        this.message = ErrorCode.CUSTOMER_NOT_FOUND.getMessage();
    }

    public ItemNotFoundException(final String message){
        super(message);
        this.error = ErrorCode.CUSTOMER_NOT_FOUND.getErrorCode();
        this.message = ErrorCode.CUSTOMER_NOT_FOUND.getMessage();
    }
}
