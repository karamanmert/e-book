package com.karamanmert.ebook.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author karamanmert
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateAuthorRequest {


    @NotBlank(message = "PARAMETER_REQUIRED")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "PARAMETER_REQUIRED")
    @JsonProperty("surname")
    private String surname;

    @NotBlank(message = "PARAMETER_REQUIRED")
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;
}
