package com.karamanmert.ebook.projection;

import java.time.LocalDate;

/**
 * @author karamanmert
 */
public interface AuthorInformationView {

    String getName();

    String getSurname();

    LocalDate getDateOfBirth();

    String getBookName();
}
