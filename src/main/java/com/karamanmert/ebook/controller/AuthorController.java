package com.karamanmert.ebook.controller;

import com.karamanmert.ebook.model.request.CreateAuthorRequest;
import com.karamanmert.ebook.service.business.AuthorBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Void> create(@RequestBody @Valid CreateAuthorRequest request) {
        authorBusinessService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
