package com.karamanmert.ebook.controller;

import com.karamanmert.ebook.model.dto.BookDto;
import com.karamanmert.ebook.model.request.CreateBookRequest;
import com.karamanmert.ebook.model.request.UpdateBookRequest;
import com.karamanmert.ebook.service.business.BookBusinessService;
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
    public ResponseEntity<Void> create(@RequestBody @Valid CreateBookRequest request) {
        businessService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<BookDto> getByName(
            @Valid @NotBlank(message = "PARAMETER_REQUIRED") @RequestParam(value = "name") String name) {
        BookDto bookDto = businessService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // todo update, delete all, page
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(
            @Valid @NotBlank(message = "PARAMETER_REQUIRED") @PathVariable(value = "id") Integer id) {
        businessService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateBookRequest request) {
        businessService.update(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<BookDto>> getPaginatedBooks(@RequestParam Integer page,
                                                           @RequestParam Integer pageSize) {
        Page<BookDto> books = businessService.getAllPaginatedBooks(page, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
}
