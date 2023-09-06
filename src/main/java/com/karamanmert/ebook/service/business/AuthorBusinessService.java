package com.karamanmert.ebook.service.business;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.mapper.AuthorMapper;
import com.karamanmert.ebook.model.dto.AuthorDto;
import com.karamanmert.ebook.model.request.CreateAuthorRequest;
import com.karamanmert.ebook.service.spec.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author karamanmert
 */
@Service
@RequiredArgsConstructor
public class AuthorBusinessService {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public void create(CreateAuthorRequest request) {
        authorService.checkByNameAndSurname(request.getName(), request.getSurname());
        var author = this.buildAuthor(request);
        authorService.save(author);
    }

    // TODO -> Dto will be change
    public AuthorDto findByBookIsbn(String isbn) {
        Author author = authorService.findByBookIsbn(isbn);
        return authorMapper.mapEntityToDto(author);
    }


    private Author buildAuthor(CreateAuthorRequest request) {
        return authorMapper.mapToEntity(request);
    }
}
