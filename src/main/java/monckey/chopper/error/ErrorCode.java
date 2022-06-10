package monckey.chopper.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    GENERIC_ERROR("AKAINU-0001", "The system is unable to complete the request. Contact system support."),
    HTTP_MEDIATYPE_NOT_SUPPORTED("KIZARU-0002", "Requested media type is not supported. Please use application/json or application/xml as 'Content-Type' header value"),
    HTTP_MESSAGE_NOT_WRITABLE("SHOPPER-0003", "Missing 'Accept' header. Please add 'Accept' header."),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE("ROBIN-0004", "Requested 'Accept' header value is not supported. Please use application/json or application/xml as 'Accept' value"),
    JSON_PARSE_ERROR("LUFFY-0005", "Make sure request payload should be a valid JSON object."),
    HTTP_MESSAGE_NOT_READABLE("SANJI-0006", "Make sure request payload should be a valid JSON or XML object according to 'Content-Type'."),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED("ZORO-0007", "Request method not supported."),
    CONSTRAINT_VIOLATION("BROOK-0008", "Validation failed."),
    ILLEGAL_ARGUMENT_EXCEPTION("NAMI-0009", "Invalid data passed."),
    RESOURCE_NOT_FOUND("FRANKY-0010", "Requested resource not found"),
    CUSTOMER_NOT_FOUND("USOPP-0011", "Requested customer not found"),
    ITEM_NOT_FOUND("SHANKS-0012", "Requested item not found"),
    GENERIC_ALREADY_EXISTS("ASCE-0013", "Already exists.");

    private final String errorCode;
    private final String message;

     ErrorCode(final String errorCode, final String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
