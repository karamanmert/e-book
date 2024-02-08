package com.karamanmert.ebook.service.spec;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.projection.AuthorInformationDto;
import com.karamanmert.ebook.projection.AuthorInformationView;

import java.util.List;

/**
 * @author karamanmert
 */
public interface AuthorService {

    void checkByNameAndSurname(String name, String surname);

    void save(Author request);

    Author findByBookIsbn(String isbn);

    List<Author> findAll();

    List<AuthorInformationView> findAllAuthorsWithBooks();

    List<AuthorInformationDto> findAllAuthorInformationDtos();
}
