package by.edu.tasklist.domain.task;

import org.springframework.web.multipart.MultipartFile;

public record TaskImage(
        MultipartFile file
) {
}
