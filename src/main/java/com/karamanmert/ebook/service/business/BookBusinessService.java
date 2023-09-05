package com.karamanmert.ebook.service.business;

import com.karamanmert.ebook.entity.Book;
import com.karamanmert.ebook.mapper.BookMapper;
import com.karamanmert.ebook.model.dto.BookDto;
import com.karamanmert.ebook.model.request.CreateBookRequest;
import com.karamanmert.ebook.model.request.UpdateBookRequest;
import com.karamanmert.ebook.service.spec.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author karamanmert
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BookBusinessService {

    private final BookService service;
    private final BookMapper bookMapper;

    public void create(CreateBookRequest request) {
        Book book = this.buildBook(request);
        book.setIsbn(service.generateISBN());
        service.save(book);
    }

    public BookDto getByName(String name) {
        Book book = service.getByName(name);
        return this.buildBookDto(book);
    }

    public List<BookDto> getAll() {
        return service.getAll()
                      .stream()
                      .map(this::buildBookDto)
                      .toList();
    }
    private Book buildBook(CreateBookRequest request) {
        return bookMapper.requestToEntity(request);
    }

    private BookDto buildBookDto(Book book) {
        return bookMapper.entityToDto(book);
    }

    public void deleteById(int id) {
        service.checkBookExistsById(id);
        service.deleteById(id);
        log.info("The book is deleted with given id:{}", id);
    }

    public void update(UpdateBookRequest request) {
        Book book = service.getByIsbn(request.getIsbn());
        bookMapper.updateBookFromRequest(request, book);
        service.save(book);
    }


    public Page<Book> getAllPaginatedBooks(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return service.getPaginatedBooks(pageRequest);
    }
}