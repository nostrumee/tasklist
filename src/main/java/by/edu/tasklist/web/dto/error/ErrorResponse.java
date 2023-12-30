package by.edu.tasklist.web.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.Map;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        int status,
        String message,
        Map<String, String> errors
) {
}
