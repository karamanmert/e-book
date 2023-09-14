package com.karamanmert.ebook.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

/**
 * @author karamanmert
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorRequest {


    @Size(min = 2, max = 10, message = "INVALID_NAME")
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
