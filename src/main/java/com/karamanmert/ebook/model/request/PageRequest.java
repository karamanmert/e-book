package com.karamanmert.ebook.model.request;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author karamanmert
 * @date 4.11.2024
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

    @Builder.Default
    @Range(min = 0, message = "INVALID_REQUEST_BODY")
    private int page = 0;

    @Builder.Default
    @Max(value = 100, message = "INVALID_REQUEST_BODY")
    private int pageSize = 20;
}
