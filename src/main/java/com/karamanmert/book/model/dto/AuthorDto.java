package com.karamanmert.book.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * @author karamanmert
 */
@SuperBuilder
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
}
