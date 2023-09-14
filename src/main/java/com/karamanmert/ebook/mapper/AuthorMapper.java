package com.karamanmert.ebook.mapper;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.model.dto.AuthorDto;
import com.karamanmert.ebook.model.request.CreateAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author karamanmert
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    Author mapToEntity(CreateAuthorRequest request);

    AuthorDto mapEntityToDto(Author author);
}
