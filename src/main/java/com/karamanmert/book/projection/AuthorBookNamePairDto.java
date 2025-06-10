package com.karamanmert.book.projection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author karamanmert
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthorBookNamePairDto {

    private String authorName;
    private String authorSurname;
    private LocalDate authorBirthDate;
    private String bookName;

    public AuthorBookNamePairDto(String authorName,
                                 String authorSurname,
                                 LocalDate authorBirthDate,
                                 String bookName) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.authorBirthDate = authorBirthDate;
        this.bookName = bookName;
    }
}
