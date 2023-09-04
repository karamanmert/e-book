package com.karamanmert.ebook.entity;

import com.karamanmert.ebook.enums.BookType;
import com.karamanmert.ebook.enums.Language;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author karamanmert
 */
@Entity
@Table(name = "book")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "edition", nullable = false)
    private Integer edition;

    @Column(name = "type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private BookType type;

    @Column(name = "language", nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Language language = Language.TR;

    @Column(name = "isbn", nullable = false, length = 13)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;
}
