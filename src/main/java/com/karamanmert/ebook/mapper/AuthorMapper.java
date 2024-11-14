package com.karamanmert.ebook.mapper;

import com.karamanmert.ebook.entity.Author;
import com.karamanmert.ebook.model.dto.AuthorDto;
import com.karamanmert.ebook.model.dto.AuthorWithBooksDto;
import com.karamanmert.ebook.model.request.CreateAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author karamanmert
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    Author mapToEntity(CreateAuthorRequest request);

    AuthorWithBooksDto mapEntityToDto(Author author);

    List<AuthorDto> mapEntitiesToDtos(List<Author> authors);

    // List<AuthorDto> mapAuthorInformationDtoToAuthorDto(List<AuthorInformationDto> informationDtos);
}
