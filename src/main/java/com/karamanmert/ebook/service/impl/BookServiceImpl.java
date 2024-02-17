package com.karamanmert.ebook.service.impl;

import com.karamanmert.ebook.entity.Book;
import com.karamanmert.ebook.enums.ErrorCode;
import com.karamanmert.ebook.exception.ApiException;
import com.karamanmert.ebook.repository.BookRepository;
import com.karamanmert.ebook.service.spec.BookService;
import com.karamanmert.ebook.util.impl.RandomNumberGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author karamanmert
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService {

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
        final List<Book> books = repository.findAll();
        if (books.isEmpty()) {
            log.info("There is no book in database!");
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.BOOK_NOT_FOUND);
        }
        return books;
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }


    @Override
    public Book getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.BOOK_NOT_FOUND));
    }

    @Override
    public void checkBookExistsById(int id) {
        final Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.BOOK_NOT_FOUND);
        }
    }

    @Override
    public String generateISBN() {
        return RandomNumberGenerator.generate();
    }

    @Override
    public Book getByIsbn(String isbn) {
        return repository
                .findByIsbn(isbn)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.ISBN_NOT_FOUND));
    }

    @Override
    public Page<Book> getPaginatedBooks(PageRequest request) {
        return repository.findAll(request);
    }
}
