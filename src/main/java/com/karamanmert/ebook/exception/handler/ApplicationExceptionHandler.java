package com.karamanmert.ebook.exception.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.karamanmert.ebook.enums.ErrorCode;
import com.karamanmert.ebook.exception.ApiException;
import com.karamanmert.ebook.exception.ErrorDetail;
import com.karamanmert.ebook.exception.ErrorResponse;
import com.karamanmert.ebook.service.impl.MessageTranslator;
import io.micrometer.common.util.StringUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.util.*;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
    /*
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        return getErrorResponseFromMethodArgumentNotValid(ex);
    }

    private ResponseEntity<Object> getErrorResponseFromMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        List<ErrorDetail> errorDetailList = new ArrayList<>();
        if (!errorList.isEmpty()) {
            errorDetailList = errorList
                    .stream()
                    .map(error -> ErrorDetail
                            .builder()
                            .errorCode(ErrorCode.toEnum(error.getDefaultMessage()))
                            .message(messageTranslator.getMessage(errorPrefix + error.getDefaultMessage()))
                            .argument(error.getField())
                            .build())
                    .collect(toList());
        } else {
            String defaultMessage = Objects.requireNonNull(ex
                                                                   .getBindingResult()
                                                                   .getAllErrors()
                                                                   .get(0)
                                                                   .getDefaultMessage());
            if (StringUtils.isBlank(defaultMessage)) {
                defaultMessage = ErrorCode.INVALID_REQUEST_BODY.name();
            }
            ErrorDetail errorDetail = ErrorDetail
                    .builder()
                    .errorCode(ErrorCode.toEnum(defaultMessage))
                    .message(messageTranslator.getMessage(errorPrefix + defaultMessage))
                    .argument("")
                    .build();
            errorDetailList.add(errorDetail);
        }

        for (ErrorDetail errorDetail : errorDetailList) {
            List<Field> declaredFields = this.getAllDeclaredFields(ex.getParameter().getParameterType());
            for (Field declaredField : declaredFields) {
                if (errorDetail.getArgument().equals(declaredField.getName()) && declaredField.isAnnotationPresent(
                        JsonProperty.class) && !declaredField.getAnnotation(JsonProperty.class).value().isEmpty()) {
                    errorDetail.setArgument(declaredField.getAnnotation(JsonProperty.class).value());
                }
            }
        }
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errorDetailList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private List<Field> getAllDeclaredFields(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            return Collections.emptyList();
        }
        List<Field> allDeclaredFields = new ArrayList<>(getAllDeclaredFields(clazz.getSuperclass()));
        List<Field> declaredFields = Arrays.stream(clazz.getDeclaredFields()).toList();
        allDeclaredFields.addAll(declaredFields);
        return allDeclaredFields;
    }
     */
}