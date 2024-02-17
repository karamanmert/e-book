package com.karamanmert.ebook.repository;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.projection.AuthorBookNamePairDto;
import com.karamanmert.ebook.projection.AuthorInformationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author karamanmert
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNameAndSurname(String name, String surname);

    @Query(value = "SELECT a FROM Author a JOIN a.books b WHERE b.isbn = :isbn")
    Optional<Author> findByBookIsbn(String isbn);

    // interface projection
    @Query("SELECT a.name AS name, a.surname as surname, a.dateOfBirth as dateOfBirth, a.books AS books " + "FROM Author a")
    List<AuthorInformationView> findAllAuthorsWithBooks();

    // dto projection
    @Query("SELECT new com.karamanmert.ebook.projection.AuthorBookNamePairDto(a.name, b.name)" + "FROM Author a " + "JOIN a.books b")
    List<AuthorBookNamePairDto> findAuthorBookNamePairDto();
}
