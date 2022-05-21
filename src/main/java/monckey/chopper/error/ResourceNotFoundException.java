package monckey.chopper.error;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private String error;
    private String message;

    public ResourceNotFoundException(ErrorCode code){
        super(code.getMessage());
        this.error = ErrorCode.RESOURCE_NOT_FOUND.getErrorCode();
        this.message = ErrorCode.RESOURCE_NOT_FOUND.getMessage();
    }

    public ResourceNotFoundException(final String message){
        super(message);
        this.error = ErrorCode.RESOURCE_NOT_FOUND.getErrorCode();
        this.message = ErrorCode.RESOURCE_NOT_FOUND.getMessage();
    }
}
