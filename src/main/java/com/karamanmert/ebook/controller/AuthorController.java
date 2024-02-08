package com.karamanmert.ebook.controller;

import com.karamanmert.ebook.model.dto.AuthorDto;
import com.karamanmert.ebook.model.request.CreateAuthorRequest;
import com.karamanmert.ebook.service.business.AuthorBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author karamanmert
 */
@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorBusinessService authorBusinessService;

    @PostMapping
    @Operation(summary = "Create an author")
    public ResponseEntity<Void> create(@Valid @RequestBody CreateAuthorRequest request) {
        authorBusinessService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{isbn}")
    @Operation(summary = "Get author by isbn")
    public ResponseEntity<AuthorDto> getByBookIsbn(
            @Valid @NotBlank(message = "PARAMETER_REQUIRED") @PathVariable(value = "isbn") String isbn) {
        AuthorDto response = authorBusinessService.findByBookIsbn(isbn);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all authors")
    public ResponseEntity<List<AuthorDto>> getAll() {
        List<AuthorDto> response = authorBusinessService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all-with-books")
    @Operation(summary = "Get all authors with books")
    public ResponseEntity<List<AuthorDto>> getAllWithBooks() {
        List<AuthorDto> response = authorBusinessService.findAuthorsWithBooks();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
