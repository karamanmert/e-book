package com.karamanmert.book.service.spec;

import com.karamanmert.book.entity.Author;
import com.karamanmert.book.model.dto.AuthorDto;
import com.karamanmert.book.projection.AuthorInformationView;

import java.util.List;

/**
 * @author karamanmert
 */
public interface AuthorService {

    void checkByNameAndSurname(String name, String surname);

    void save(Author request);

    Author findByBookIsbn(String isbn);

    List<AuthorDto> getAllAuthors();

    List<AuthorInformationView> getAllAuthorsWithBooks();
}
