package com.karamanmert.ebook.mapper;

import com.karamanmert.ebook.entity.Book;
import com.karamanmert.ebook.model.dto.BookDto;
import com.karamanmert.ebook.model.request.CreateBookRequest;
import com.karamanmert.ebook.model.request.UpdateBookRequest;
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
