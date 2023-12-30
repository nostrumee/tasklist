package by.edu.tasklist.web.dto.task;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record TaskImageDto(

        @NotNull(message = "Image must be not null.")
        MultipartFile file
) {
}
