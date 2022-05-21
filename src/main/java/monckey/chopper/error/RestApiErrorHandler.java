package monckey.chopper.error;

import com.fasterxml.jackson.core.JsonParseException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Locale;

@ControllerAdvice
public class RestApiErrorHandler {

    private MessageSource messageSource;

    @Autowired
    public RestApiErrorHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(HttpServletRequest request, Locale locale, Exception e){
        e.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.GENERIC_ERROR.getErrorCode(),
                ErrorCode.GENERIC_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod())
                .setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException exception, Locale locale){
       exception.printStackTrace();
       Error error = ErrorUtils.createError(ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrorCode(),
               ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
               .setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod()).setTime(Instant.now());
       return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<Error> handleHttpMessageNotWritableException(HttpServletRequest request, HttpMessageNotWritableException exception, Locale locale){
        exception.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getErrorCode(), ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getMessage(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod()).setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotAcceptableException(HttpServletRequest request, HttpMediaTypeNotAcceptableException exception, Locale locale){
        exception.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrorCode(), ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod()).setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception, Locale locale){
        exception.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.HTTP_MESSAGE_NOT_READABLE.getErrorCode(), ErrorCode.HTTP_MESSAGE_NOT_READABLE.getMessage(), HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod()).setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Error> handleJsonParseException(HttpServletRequest request, JsonParseException exception, Locale locale){
        exception.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.JSON_PARSE_ERROR.getErrorCode(), ErrorCode.JSON_PARSE_ERROR.getMessage(), HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod()).setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Error> handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException exception, Locale locale){
        exception.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED.getErrorCode(), ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED.getMessage(), HttpStatus.NOT_IMPLEMENTED.value())
                .setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod()).setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleHIllegalArgumentException(HttpServletRequest request, IllegalArgumentException exception, Locale locale){
        exception.printStackTrace();
        Error error = ErrorUtils.createError(ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getErrorCode(), String.format("%s%s", ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getMessage()
                , exception.getMessage()), HttpStatus.BAD_REQUEST.value()).setUrl(request.getRequestURL().toString()).setReqMethod(request.getMethod()).setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException exception, Locale locale){
        Error error = ErrorUtils.createError(ErrorCode.RESOURCE_NOT_FOUND.getErrorCode(),String.format("%s%s",  ErrorCode.RESOURCE_NOT_FOUND.getMessage(), exception.getMessage()), HttpStatus.NOT_FOUND.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Error> handleCustomerNotFoundException(HttpServletRequest request, CustomerNotFoundException exception, Locale locale){
        Error error = ErrorUtils.createError(ErrorCode.CUSTOMER_NOT_FOUND.getErrorCode(), String.format("%s%s", ErrorCode.CUSTOMER_NOT_FOUND.getMessage(), exception.getMessage()), HttpStatus.NOT_FOUND.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Error> handleItemNotFoundException(HttpServletRequest request, ItemNotFoundException exception, Locale locale){
        Error error = ErrorUtils.createError(ErrorCode.ITEM_NOT_FOUND.getErrorCode(), String.format("%s%s", ErrorCode.ITEM_NOT_FOUND.getMessage(), exception.getMessage()), HttpStatus.NOT_FOUND.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenericAlreadyExistsException.class)
    public ResponseEntity<Error> handleGenericAlreadyExistsException(HttpServletRequest request, GenericAlreadyExistsException exception, Locale locale){
        Error error = ErrorUtils.createError(ErrorCode.GENERIC_ALREADY_EXISTS.getErrorCode(), String.format("%s%s", ErrorCode.GENERIC_ALREADY_EXISTS.getMessage(), exception.getMessage()), HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException exception, Locale locale){
        Error error = ErrorUtils.createError(ErrorCode.CONSTRAINT_VIOLATION.getErrorCode(), String.format("%s%s", ErrorCode.CONSTRAINT_VIOLATION.getMessage(), exception.getMessage()), HttpStatus.BAD_REQUEST.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod())
                .setTime(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
