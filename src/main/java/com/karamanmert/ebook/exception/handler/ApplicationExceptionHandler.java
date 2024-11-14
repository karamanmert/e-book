package com.karamanmert.ebook.exception.handler;

import com.karamanmert.ebook.enums.ErrorCode;
import com.karamanmert.ebook.exception.ApiException;
import com.karamanmert.ebook.exception.ErrorDetail;
import com.karamanmert.ebook.exception.ErrorResponse;
import com.karamanmert.ebook.service.impl.MessageTranslator;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler {

    private final MessageTranslator messageTranslator;

    @Value("${messages.default.prefix}")
    private String errorPrefix;


    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ae) {
        ErrorDetail errorDetail = ErrorDetail
                .builder()
                .errorCode(ae.getErrorCode())
                .message(messageTranslator.getMessage(errorPrefix + ae.getErrorCode()))
                .build();
        List<ErrorDetail> errorDetailList = new ArrayList<>();
        errorDetailList.add(errorDetail);
        ErrorResponse response = new ErrorResponse();
        response.setErrors(errorDetailList);
        return ResponseEntity.status(ae.getHttpStatus()).body(response);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = messageTranslator.getMessage(errorPrefix + error.getDefaultMessage());
            errorMap.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

        final ArrayList<ErrorDetail> errorDetails = new ArrayList<>();
        ex.getConstraintViolations()
                .forEach(constraintViolation -> {
                    ErrorDetail errorDetail = new ErrorDetail();
                    errorDetail.setErrorCode(ErrorCode.toEnum(constraintViolation.getMessage()));
                    errorDetail.setMessage(messageTranslator.getMessage(errorPrefix + constraintViolation.getMessage()));
                    errorDetails.add(errorDetail);
                });
        ErrorResponse response = new ErrorResponse();
        response.setErrors(errorDetails);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}