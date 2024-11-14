package com.karamanmert.ebook.service.business;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.entity.Book;
import com.karamanmert.ebook.mapper.AuthorMapper;
import com.karamanmert.ebook.mapper.BookMapper;
import com.karamanmert.ebook.model.dto.AuthorDto;
import com.karamanmert.ebook.model.dto.AuthorWithBooksDto;
import com.karamanmert.ebook.model.dto.BookDto;
import com.karamanmert.ebook.model.request.CreateAuthorRequest;
import com.karamanmert.ebook.model.response.AuthorWithBooksResponse;
import com.karamanmert.ebook.projection.AuthorBookNamePairDto;
import com.karamanmert.ebook.projection.AuthorInformationView;
import com.karamanmert.ebook.service.spec.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public AuthorWithBooksDto findByBookIsbn(String isbn) {
        final Author author = authorService.findByBookIsbn(isbn);
        final AuthorWithBooksDto dto = new AuthorWithBooksDto();
        dto.setName(author.getName());
        dto.setSurname(author.getSurname());
        dto.setDateOfBirth(author.getDateOfBirth());
        final Optional<Book> firstBook = author.getBooks().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
        firstBook.ifPresent(book -> dto.setBooks(Collections.singleton(bookMapper.entityToDto(book))));
        return dto;
    }

    // (interface projection)
    public List<AuthorWithBooksResponse> getAllWithBooks() {
        final List<AuthorInformationView> allAuthorsWithBooks = authorService.getAllAuthorsWithBooks();

        final Map<String, AuthorWithBooksResponse> authorBooksMap = allAuthorsWithBooks.stream()
                .collect(getAuthorInformationViewMapCollector());

        return new ArrayList<>(authorBooksMap.values());
    }

    public List<AuthorDto> getAll() {
        return authorService.getAllAuthors();
    }

    private Collector<AuthorInformationView, ?, Map<String, AuthorWithBooksResponse>> getAuthorInformationViewMapCollector() {
        return Collectors.groupingBy(
                author -> author.getName() + " " + author.getSurname(),
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        authorInfoList -> {
                            AuthorInformationView first = authorInfoList.get(0);
                            Set<String> books = authorInfoList.stream()
                                    .map(AuthorInformationView::getBookName)
                                    .collect(Collectors.toSet());
                            return getAuthorWithBooksResponse(first, books);
                        }
                )
        );
    }

    private AuthorWithBooksResponse getAuthorWithBooksResponse(AuthorInformationView first, Set<String> books) {
        AuthorWithBooksResponse response = new AuthorWithBooksResponse();
        response.setName(first.getName());
        response.setSurname(first.getSurname());
        response.setDateOfBirth(first.getDateOfBirth());
        response.setBookName(books);
        return response;
    }

    private Author buildAuthor(CreateAuthorRequest request) {
        return authorMapper.mapToEntity(request);
    }
}
