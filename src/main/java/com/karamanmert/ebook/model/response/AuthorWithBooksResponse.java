package com.karamanmert.ebook.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.karamanmert.ebook.model.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorWithBooksResponse extends AuthorDto {

    @JsonProperty("books")
    private Set<String> bookName;
}
