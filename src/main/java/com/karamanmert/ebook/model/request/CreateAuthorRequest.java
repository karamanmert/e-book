package com.karamanmert.ebook.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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

    @NotBlank(message = "PARAMETER_REQUIRED")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "PARAMETER_REQUIRED")
    @JsonProperty("surname")
    private String surname;

    @NotNull(message = "PARAMETER_REQUIRED")
    @Past(message = "INVALID_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
}
