package com.karamanmert.ebook.service.business;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.enums.ErrorCode;
import com.karamanmert.ebook.exception.ApiException;
import com.karamanmert.ebook.mapper.AuthorMapper;
import com.karamanmert.ebook.model.request.CreateAuthorRequest;
import com.karamanmert.ebook.service.spec.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;

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


    private Author buildAuthor(CreateAuthorRequest request) {
        return authorMapper.mapToEntity(request);
    }
}
