package com.karamanmert.book.service.impl;

import com.karamanmert.common_lib.enums.ErrorCode;
import com.karamanmert.book.entity.Author;
import com.karamanmert.book.exception.ApiException;
import com.karamanmert.book.model.dto.AuthorDto;
import com.karamanmert.book.projection.AuthorInformationView;
import com.karamanmert.book.repository.AuthorRepository;
import com.karamanmert.book.service.spec.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.AUTHOR_ALREADY_EXISTS);
        }
    }

    @Override
    public void save(Author author) {
        repository.save(author);
    }

    @Override
    public Author findByBookIsbn(String isbn) {
        return repository.findByBookIsbn(isbn)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.ISBN_NOT_FOUND));
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return repository.findAllAuthors();
    }

    @Override
    public List<AuthorInformationView> getAllAuthorsWithBooks() {
        return repository.findAllAuthorsWithBooks();
    }
}
