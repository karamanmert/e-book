package com.karamanmert.ebook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author karamanmert
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorInformationDto {

    String name;
    String surname;
    LocalDate dateOfBirth;
    Set<BookDto> books;
}
