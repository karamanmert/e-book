package com.karamanmert.ebook.projection;

import com.karamanmert.ebook.model.dto.BookDto;
import lombok.Builder;
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
@Builder
public class AuthorInformationDto {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private BookDto bookDto;

    public AuthorInformationDto(String name, String surname, LocalDate dateOfBirth, BookDto bookDto) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.bookDto = bookDto;
    }
}
