package com.karamanmert.ebook.service.spec;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.model.dto.AuthorDto;
import com.karamanmert.ebook.model.dto.AuthorWithBooksDto;
import com.karamanmert.ebook.projection.AuthorBookNamePairDto;
import com.karamanmert.ebook.projection.AuthorInformationView;

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
