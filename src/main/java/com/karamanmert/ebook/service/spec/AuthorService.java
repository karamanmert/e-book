package com.karamanmert.ebook.service.spec;

import com.karamanmert.ebook.entity.Author;

/**
 * @author karamanmert
 */
public interface AuthorService {

    void checkByNameAndSurname(String name, String surname);

    void save(Author request);

    Author findByBookIsbn(String isbn);
}
