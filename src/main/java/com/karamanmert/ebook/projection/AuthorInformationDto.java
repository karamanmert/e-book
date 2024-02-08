package com.karamanmert.ebook.projection;

import com.karamanmert.ebook.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author karamanmert
 */

@Getter
@Setter
@NoArgsConstructor
@Builder
public class AuthorInformationDto {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private Set<Book> books;

    public AuthorInformationDto(String name, String surname, LocalDate dateOfBirth, Set<Book> books) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.books = books;
    }
}
