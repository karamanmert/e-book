package com.karamanmert.ebook.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.karamanmert.ebook.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetail {

    @JsonProperty("error_code")
    private ErrorCode errorCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("argument")
    private String argument;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("details")
    private Map<String, Object> details;
}