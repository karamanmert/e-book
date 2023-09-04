package com.karamanmert.ebook.service.spec;

import com.karamanmert.ebook.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author karamanmert
 */
public interface BookService {

    void save(Book book);

    Book getByName(String name);

    List<Book> getAll();

    void deleteById(int id);

    Integer getById(int id);

    void checkBookExistsById(int id);

    String generateISBN();

    Book getByIsbn(String isbn);

    Page<Book> getPaginatedBooks(PageRequest request);
}
