package com.karamanmert.ebook.controller;

import com.karamanmert.ebook.model.dto.BookDto;
import com.karamanmert.ebook.model.request.CreateBookRequest;
import com.karamanmert.ebook.model.request.PageRequest;
import com.karamanmert.ebook.model.request.UpdateBookRequest;
import com.karamanmert.ebook.service.business.BookBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author karamanmert
 */
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookBusinessService businessService;

    @PostMapping
    @Operation(summary = "Create a book")
    public ResponseEntity<Void> create(@RequestBody @Valid CreateBookRequest request) {
        businessService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get book by name")
    public ResponseEntity<BookDto> getByName(
            @Valid @NotBlank(message = "PARAMETER_REQUIRED") @RequestParam(value = "name") String name) {
        BookDto bookDto = businessService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @GetMapping("{isbn}")
    @Operation(summary = "Get book by ISBN")
    public ResponseEntity<BookDto> getByIsbn(
            @Valid @NotBlank(message = "PARAMETER_REQUIRED") @PathVariable(value = "isbn") String isbn) {
        BookDto response = businessService.getByIsbn(isbn);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all books")
    public ResponseEntity<List<BookDto>> getAll() {
        List<BookDto> response = businessService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete book by id")
    public ResponseEntity<Void> deleteById(
            @Valid @NotBlank(message = "PARAMETER_REQUIRED") @PathVariable(value = "id") Integer id) {
        businessService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update")
    @Operation(summary = "Update book")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateBookRequest request) {
        businessService.update(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/paginate")
    @Operation(summary = "Get paginated books")
    public ResponseEntity<Page<BookDto>> getPaginatedBooks(@Valid PageRequest request) {
        Page<BookDto> books = businessService.getAllPaginatedBooks(request);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
}
