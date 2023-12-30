package by.edu.tasklist.web.dto.task;

import by.edu.tasklist.domain.task.Status;
import by.edu.tasklist.web.validation.OnCreate;
import by.edu.tasklist.web.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record TaskDto(

        @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
        Long id,

        @NotNull(message = "Title must be not null.",
                groups = {OnCreate.class, OnUpdate.class})
        @Length(max = 255,
                message = "Title length must be smaller than 255 symbols.",
                groups = {OnCreate.class, OnUpdate.class})
        String title,

        @Length(max = 255,
                message = "Description length must be smaller than 255 symbols.",
                groups = {OnCreate.class, OnUpdate.class})
        String description,

        Status status,

        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime expirationDate,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        List<String> images
) {
}
