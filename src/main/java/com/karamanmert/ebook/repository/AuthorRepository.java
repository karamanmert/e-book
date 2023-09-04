package com.karamanmert.ebook.repository;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author karamanmert
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNameAndSurname(String name, String surname);

    @Query(nativeQuery = true,
    value = "SELECT * FROM author WHERE boo")
    Optional<Author> findByBook(Book book);
}
