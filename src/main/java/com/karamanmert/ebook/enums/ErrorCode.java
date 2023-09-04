package com.karamanmert.ebook.enums;

/**
 * @author karamanmert
 */
public enum ErrorCode {

    SYSTEM_ERROR,
    PARAMETER_REQUIRED,
    INVALID_REQUEST_BODY,
    NO_ERROR_CODE_FOUND,
    DUBLICATE_AUTHOR,
    BOOK_NOT_FOUND, ISBN_NOT_FOUND;

    public static ErrorCode toEnum(String value) {
        try {
            return ErrorCode.valueOf(value);
        } catch (Exception e) {
            return ErrorCode.NO_ERROR_CODE_FOUND;
        }
    }
}
