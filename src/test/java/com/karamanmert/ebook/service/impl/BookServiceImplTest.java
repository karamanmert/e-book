package com.karamanmert.ebook.service.impl;

import com.karamanmert.ebook.entity.Book;
import com.karamanmert.ebook.enums.ErrorCode;
import com.karamanmert.ebook.exception.ApiException;
import com.karamanmert.ebook.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author karamanmert
 */
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookServiceImpl service;

    @Test
    void shouldSave() {
        // Given
        Book book = new Book();
        when(repository.save(any(Book.class))).thenReturn(book);
        // When
        service.save(book);
        // Then
        verify(repository, times(1)).save(any(Book.class));
    }

    @Test
    void shouldGetByName() {
        // Given
        when(repository.findByName(anyString())).thenReturn(Optional.of(new Book()));

        // When
        service.getByName(anyString());

        // Then
        verify(repository).findByName(anyString());
    }

    @Test
    void shouldThrowExceptionWhenTryingToGetByName() {
        // Given
        when(repository.findByName(anyString())).thenReturn(Optional.empty());

        // When
        ApiException exception = assertThrows(ApiException.class, () -> service.getByName(anyString()));

        // Then
        assertEquals(ErrorCode.BOOK_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void shouldGetAllBooks() {
        // Given
        List<Book> expectedBooks = Arrays.asList(new Book(), new Book());

        when(repository.findAll()).thenReturn(expectedBooks);

        // When
        var books = service.getAll();

        // Then
        assertNotNull(books);
        assertEquals(expectedBooks.size(), books.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldThrowExceptionWhenTryingToGetAllBooks() {
        // Given
        when(repository.findAll()).thenReturn(List.of());

        // When
        ApiException exception = assertThrows(ApiException.class, () -> service.getAll());

        // Then
        verify(repository, times(1)).findAll();
        assertEquals(ErrorCode.BOOK_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void shouldDeleteBookById() {
        // Given
        int bookId = 1;

        // When
        service.deleteById(bookId);

        // Then
        verify(repository, Mockito.times(1)).deleteById(bookId);
    }

    @Test
    void shouldGetBookById() {
        // Given
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.of(new Book()));
        // When
        Book book = service.getById(id);

        // Then
        assertNotNull(book);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenTryingToGetBookById() {
        // Given
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        // When
        ApiException exception = assertThrows(ApiException.class, () -> service.getById(anyInt()));

        // Then
        verify(repository, times(1)).findById(anyInt());
        assertEquals(ErrorCode.BOOK_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void shouldCheckBookExistsById() {
        // Given
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.of(new Book()));

        // When
        service.checkBookExistsById(id);

        // Then
        verify(repository, times(1)).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenTryingToCheckBookExistsById() {
        // Given
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        // When
        ApiException exception = assertThrows(ApiException.class, () -> service.getById(anyInt()));

        // Then
        verify(repository, times(1)).findById(anyInt());
        assertEquals(ErrorCode.BOOK_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void shouldGenerateISBN() {
        // Given
        int expectedIsbnLength = 13;

        // When
        String generatedIsbn = service.generateISBN();

        // Then
        assertNotNull(generatedIsbn);
        assertEquals(expectedIsbnLength, generatedIsbn.length());
    }

}