package com.karamanmert.ebook.service.business;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.entity.Book;
import com.karamanmert.ebook.mapper.AuthorMapper;
import com.karamanmert.ebook.mapper.BookMapper;
import com.karamanmert.ebook.model.dto.AuthorDto;
import com.karamanmert.ebook.model.request.CreateAuthorRequest;
import com.karamanmert.ebook.service.spec.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author karamanmert
 */
@Service
@RequiredArgsConstructor
public class AuthorBusinessService {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    public void create(CreateAuthorRequest request) {
        authorService.checkByNameAndSurname(request.getName(), request.getSurname());
        final var author = this.buildAuthor(request);
        authorService.save(author);
    }

    public AuthorDto findByBookIsbn(String isbn) {
        final Author author = authorService.findByBookIsbn(isbn);
        AuthorDto dto = new AuthorDto();
        dto.setName(author.getName());
        dto.setSurname(author.getSurname());
        dto.setDateOfBirth(author.getDateOfBirth());
        Optional<Book> firstBook = author.getBooks().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
        firstBook.ifPresent(book -> dto.setBook(bookMapper.entityToDto(book)));
        return dto;
    }

    public List<AuthorDto> findAll() {
        final List<Author> authors = authorService.findAll();
        if (authors.isEmpty()) {
            return List.of();
        }
        return authors
                .stream()
                .map(authorMapper::mapEntityToDto)
                .toList();
    }


    private Author buildAuthor(CreateAuthorRequest request) {
        return authorMapper.mapToEntity(request);
    }
}
