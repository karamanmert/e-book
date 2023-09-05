package com.karamanmert.ebook.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
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
    @Size(min = 13, max = 13, message = "PARAMETER_REQUIRED")
    private String isbn;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private String type;

    @JsonProperty("language")
    @Enumerated(EnumType.STRING)
    private String language;

    @JsonProperty("edition")
    private int edition;
}
