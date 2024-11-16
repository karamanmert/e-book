package com.karamanmert.ebook.enums;

/**
 * @author karamanmert
 */
public enum ErrorCode {

    PARAMETER_REQUIRED,
    SYSTEM_ERROR,
    NO_ERROR_CODE_FOUND,
    INVALID_REQUEST_BODY,
    AUTHOR_ALREADY_EXISTS,
    BOOK_NOT_FOUND,
    ISBN_NOT_FOUND,
    INVALID_DATE_OF_BIRTH,
    INVALID_PARAMETER_LENGTH;

    public static ErrorCode toEnum(String value) {
        try {
            return ErrorCode.valueOf(value);
        } catch (Exception e) {
            return ErrorCode.NO_ERROR_CODE_FOUND;
        }
    }
}
