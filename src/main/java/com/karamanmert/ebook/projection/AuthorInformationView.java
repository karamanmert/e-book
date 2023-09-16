package com.karamanmert.ebook.projection;

import com.karamanmert.ebook.entity.Book;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author karamanmert
 */
public interface AuthorInformationView {

    String getName();

    String getSurname();

    LocalDate getDateOfBirth();

    Set<Book> getBooks();
}
