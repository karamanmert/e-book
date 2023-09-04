package com.karamanmert.ebook.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageTranslator {

    private final MessageSource messageSource;

    public String getMessage(String messageCode) {
        try {
            return messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return "";
        }
    }
}