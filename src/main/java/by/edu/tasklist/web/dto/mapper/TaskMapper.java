package by.edu.tasklist.web.dto.mapper;

import by.edu.tasklist.domain.task.Task;
import by.edu.tasklist.web.dto.task.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);

    List<TaskDto> toDto(List<Task> tasks);

    @Mapping(target = "status", source = "status", defaultValue = "TODO")
    Task toEntity(TaskDto dto);
}
