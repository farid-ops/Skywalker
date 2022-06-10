package monckey.chopper.error;

import lombok.Getter;

@Getter
public class GenericAlreadyExistsException extends RuntimeException{

    private final String error;
    private final String message;

    public GenericAlreadyExistsException(ErrorCode code){
        super(code.getMessage());
        this.error = ErrorCode.RESOURCE_NOT_FOUND.getErrorCode();
        this.message = ErrorCode.RESOURCE_NOT_FOUND.getMessage();
    }

    public GenericAlreadyExistsException(final String message){
        super(message);
        this.error = ErrorCode.RESOURCE_NOT_FOUND.getErrorCode();
        this.message = ErrorCode.RESOURCE_NOT_FOUND.getMessage();
    }
}
