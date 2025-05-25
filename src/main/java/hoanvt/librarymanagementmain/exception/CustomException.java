package hoanvt.librarymanagementmain.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final String code;
    private final HttpStatus httpStatus;

    public CustomException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
