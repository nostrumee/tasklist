package by.edu.tasklist.web.mappers;

import by.edu.tasklist.domain.task.Task;
import by.edu.tasklist.web.dto.task.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {
}
