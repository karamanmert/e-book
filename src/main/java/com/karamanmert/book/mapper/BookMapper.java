package com.karamanmert.book.mapper;

import com.karamanmert.book.entity.Book;
import com.karamanmert.book.model.dto.BookDto;
import com.karamanmert.book.model.request.CreateBookRequest;
import com.karamanmert.book.model.request.UpdateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * @author karamanmert
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    @Mapping(target = "author.id", source = "authorId")
    @Mapping(target = "language", source = "language")
    Book requestToEntity(CreateBookRequest request);

    @Mapping(target = "authorName", expression = "java(book.getAuthor().getName() + ' ' + book.getAuthor().getSurname())")
    BookDto entityToDto(Book book);

    @Mapping(target = "book.id", ignore = true)
    @Mapping(target = "book.isbn", ignore = true)
    @Mapping(target = "book.author", ignore = true)
    void updateBookFromRequest(UpdateBookRequest request, @MappingTarget Book book);
}
