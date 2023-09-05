package com.karamanmert.ebook.service.impl;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.enums.ErrorCode;
import com.karamanmert.ebook.exception.ApiException;
import com.karamanmert.ebook.repository.AuthorRepository;
import com.karamanmert.ebook.service.spec.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author karamanmert
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public void checkByNameAndSurname(String name, String surname) {
        Optional<Author> optionalAuthor = repository.findByNameAndSurname(name, surname);
        if (optionalAuthor.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.DUBLICATE_AUTHOR);
        }
    }

    @Override
    public void save(Author author) {
        repository.save(author);
    }
}