package com.karamanmert.ebook.service.impl;

import com.karamanmert.ebook.entity.Book;
import com.karamanmert.ebook.enums.ErrorCode;
import com.karamanmert.ebook.exception.ApiException;
import com.karamanmert.ebook.repository.BookRepository;
import com.karamanmert.ebook.service.spec.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author karamanmert
 */
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private static final int ISBN_SIZE = 13;

    private final BookRepository repository;

    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public Book getByName(String name) {
        return repository
                .findByName(name)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.BOOK_NOT_FOUND));
    }

    @Override
    public List<Book> getAll() {
        return Optional
                .ofNullable(repository.findAll())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.BOOK_NOT_FOUND))
                .stream()
                .toList();
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }


    @Override
    public Integer getById(int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.BOOK_NOT_FOUND));
    }

    @Override
    public void checkBookExistsById(int id) {
        repository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.BOOK_NOT_FOUND));
    }

    @Override
    public String generateISBN() {
        StringBuilder isbn = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < ISBN_SIZE; i++) {
            isbn.append(random.nextInt(10));
        }
        return isbn.toString();
    }

    @Override
    public Book getByIsbn(String isbn) {
        return repository.findByIsbn(isbn)
                         .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.ISBN_NOT_FOUND));
    }

    @Override
    public Page<Book> getPaginatedBooks(PageRequest request) {
        return repository.findAll(request);
    }
}
