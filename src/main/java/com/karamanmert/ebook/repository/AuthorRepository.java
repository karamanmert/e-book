package com.karamanmert.ebook.repository;

import com.karamanmert.ebook.entity.Author;
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

    @Query(value = "SELECT a FROM Author a JOIN a.books b WHERE b.isbn = :isbn")
    Optional<Author> findByBookIsbn(String isbn);
}
