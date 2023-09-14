package com.karamanmert.ebook.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author karamanmert
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("book")
    private BookDto book;
}
