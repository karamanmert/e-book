package com.karamanmert.ebook.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.karamanmert.ebook.enums.BookType;
import com.karamanmert.ebook.enums.Language;
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
public class BookDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("release_date")
    private LocalDate releaseDate;

    @JsonProperty("edition")
    private int edition;

    @JsonProperty("type")
    private BookType type;

    @JsonProperty("language")
    private Language language;

    @JsonProperty("author_name")
    private String authorName;
}
