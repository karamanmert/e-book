package com.karamanmert.book.mapper;

import com.karamanmert.book.entity.Author;
import com.karamanmert.book.model.dto.AuthorDto;
import com.karamanmert.book.model.dto.AuthorWithBooksDto;
import com.karamanmert.book.model.request.CreateAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author karamanmert
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    Author mapToEntity(CreateAuthorRequest request);

    AuthorWithBooksDto mapEntityToDto(Author author);

    List<AuthorDto> mapEntitiesToDtos(List<Author> authors);

    // List<AuthorDto> mapAuthorInformationDtoToAuthorDto(List<AuthorInformationDto> informationDtos);
}
