package com.karamanmert.ebook.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author karamanmert
 */
@Getter
@Setter
@ToString
public class CreateBookRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("release_date")
    private LocalDate releaseDate;

    @JsonProperty("edition")
    private Integer edition;

    @JsonProperty("type")
    private String type;

    @JsonProperty("author_id")
    private Long authorId;
}
