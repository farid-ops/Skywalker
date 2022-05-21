package monckey.chopper.error;

public class ErrorUtils {

    public ErrorUtils(){
        super();
    }

    /*
    * create and return Error
    * url
    * method
    * status
    * message
    * code
    * */
    public static Error createError(final String errorCode, String message, Integer status){
        Error error = new Error();
        error.setError(errorCode);
        error.setMessage(message);
        error.setStatus(status);
        return error;
    }
}
