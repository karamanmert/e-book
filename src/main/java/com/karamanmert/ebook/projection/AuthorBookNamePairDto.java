package com.karamanmert.ebook.projection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author karamanmert
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthorBookNamePairDto {

    private String authorName;
    private String bookName;

    public AuthorBookNamePairDto(String authorName, String bookName) {
        this.authorName = authorName;
        this.bookName = bookName;
    }
}
