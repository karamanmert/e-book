package com.karamanmert.ebook.exception;

import com.karamanmert.ebook.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class ApiException extends RuntimeException {

    private final int httpStatus;
    private final ErrorCode errorCode;
    private final String body;
    private final Map<String, Object> details;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.name());
        this.httpStatus = 400;
        this.errorCode = errorCode;
        this.body = "";
        this.details = Map.of();
    }

    public ApiException(HttpStatus httpStatus, ErrorCode errorCode) {
        super(errorCode.name());
        this.httpStatus = httpStatus.value();
        this.errorCode = errorCode;
        this.body = "";
        this.details = Map.of();
    }

    public ApiException(int httpStatus, ErrorCode errorCode) {
        super(errorCode.name());
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.body = "";
        this.details = Map.of();
    }

    public ApiException(int httpStatus, String body) {
        super(body);
        this.httpStatus = httpStatus;
        this.body = body;
        this.errorCode = ErrorCode.SYSTEM_ERROR;
        this.details = Map.of();
    }

    public ApiException(String body) {
        this.httpStatus = 400;
        this.body = body;
        this.errorCode = ErrorCode.SYSTEM_ERROR;
        this.details = Map.of();
    }

    public ApiException(ErrorCode errorCode, Map<String, Object> details) {
        super(errorCode.name());
        this.httpStatus = 400;
        this.errorCode = errorCode;
        this.body = "";
        this.details = details;
    }

    public ApiException(int httpStatus, ErrorCode errorCode, Map<String, Object> details) {
        super(errorCode.name());
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.body = "";
        this.details = details;
    }
}