package com.karamanmert.ebook.service.impl;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.enums.ErrorCode;
import com.karamanmert.ebook.exception.ApiException;
import com.karamanmert.ebook.model.dto.AuthorDto;
import com.karamanmert.ebook.projection.AuthorInformationView;
import com.karamanmert.ebook.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.karamanmert.ebook.enums.ErrorCode.AUTHOR_ALREADY_EXISTS;
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
        assertEquals(AUTHOR_ALREADY_EXISTS, exception.getErrorCode());
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

    @Test
    void shouldGetAllAuthors() {
        // Given
        AuthorDto authorDto = new AuthorDto();
        AuthorDto authorDto2 = new AuthorDto();
        List<AuthorDto> expected = List.of(authorDto, authorDto2);
        when(repository.findAllAuthors()).thenReturn(expected);

        // When
        List<AuthorDto> allAuthors = authorServiceImpl.getAllAuthors();

        // Then
        verify(repository).findAllAuthors();
        assertEquals(expected.size(), allAuthors.size());
    }

    @Test
    void shouldGetAllAuthorsWithBooks() {
        // Given
        AuthorInformationView authorInformationView = getAuthorInformationView();
        List<AuthorInformationView> expected = List.of(authorInformationView);
        when(repository.findAllAuthorsWithBooks()).thenReturn(expected);

        // When
        List<AuthorInformationView> response = repository.findAllAuthorsWithBooks();

        // Then
        verify(repository).findAllAuthorsWithBooks();
        assertEquals(expected.get(0).getName(), response.get(0).getName());
        assertEquals(expected.get(0).getSurname(), response.get(0).getSurname());
        assertEquals(expected.get(0).getDateOfBirth(), response.get(0).getDateOfBirth());
        assertEquals(expected.get(0).getBookName(), response.get(0).getBookName());
    }

    private AuthorInformationView getAuthorInformationView() {
        // Create a mock for AuthorInformationView
        AuthorInformationView authorInformationView = Mockito.mock(AuthorInformationView.class);

        // Set up mock behavior
        Mockito.when(authorInformationView.getName()).thenReturn("John");
        Mockito.when(authorInformationView.getSurname()).thenReturn("Doe");
        Mockito.when(authorInformationView.getDateOfBirth()).thenReturn(LocalDate.of(1985, 5, 15));
        Mockito.when(authorInformationView.getBookName()).thenReturn("Sample Book");

        return authorInformationView;
    }
}
