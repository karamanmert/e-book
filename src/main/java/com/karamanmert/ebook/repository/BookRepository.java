package com.karamanmert.ebook.repository;

import com.karamanmert.ebook.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author karamanmert
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE name =:name")
    Optional<Book> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM book")
    List<Book> findAll();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM book WHERE id =:id")
    void deleteById(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE id =:id")
    Optional<Integer> findById(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE isbn =:isbn")
    Optional<Book> findByIsbn(String isbn);
}
