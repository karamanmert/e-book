package com.karamanmert.ebook.service.impl;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.enums.ErrorCode;
import com.karamanmert.ebook.exception.ApiException;
import com.karamanmert.ebook.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.karamanmert.ebook.enums.ErrorCode.DUBLICATE_AUTHOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorServiceImpl authorServiceImpl;

    @Test
    void shouldSave() {
        // Given
        var author = new Author();

        // When
        authorServiceImpl.save(author);

        // Then
        verify(repository).save(author);
    }

    @Test
    void shouldThrowExceptionWhenThereIsAuthorByNameAndSurnameExists() {
        // Given
        when(repository.findByNameAndSurname(anyString(), anyString())).thenReturn(Optional.of(new Author()));

        // When
        ApiException exception = assertThrows(ApiException.class,
                                                 () -> authorServiceImpl.checkByNameAndSurname(anyString(), anyString()));

        // Then
        assertEquals(DUBLICATE_AUTHOR, exception.getErrorCode());
    }

    @Test
    void shouldCheckByNameAndSurname() {
        // Given
        when(repository.findByNameAndSurname(anyString(), anyString())).thenReturn(Optional.empty());

        // When
        authorServiceImpl.checkByNameAndSurname(anyString(), anyString());

        // Then
        verify(repository).findByNameAndSurname(anyString(), anyString());
    }

    @Test
    void shouldFindByBookIsbn() {
        // Given
        when(repository.findByBookIsbn(anyString())).thenReturn(Optional.of(new Author()));

        // When
        authorServiceImpl.findByBookIsbn(anyString());

        // Then
        verify(repository).findByBookIsbn(anyString());
    }

    @Test
    void shouldThrowExceptionWhenTryingToFindByBookIsbn() {
        // Given
        when(repository.findByBookIsbn(anyString())).thenReturn(Optional.empty());

        // When
        ApiException exception = assertThrows(ApiException.class,
                                              () -> authorServiceImpl.findByBookIsbn(anyString()));

        // Then
        verify(repository).findByBookIsbn(anyString());
        assertEquals(ErrorCode.ISBN_NOT_FOUND, exception.getErrorCode());
    }
}
