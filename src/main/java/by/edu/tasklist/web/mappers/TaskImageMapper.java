package by.edu.tasklist.web.mappers;

import by.edu.tasklist.domain.task.TaskImage;
import by.edu.tasklist.web.dto.task.TaskImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends Mappable<TaskImage, TaskImageDto> {
}
