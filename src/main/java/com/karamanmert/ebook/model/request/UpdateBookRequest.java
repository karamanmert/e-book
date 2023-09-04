package com.karamanmert.ebook.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author karamanmert
 */

@Getter
@Setter
@ToString
public class UpdateBookRequest {

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("language")
    private String language;

    @JsonProperty("edition")
    private int edition;
}
