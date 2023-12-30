package by.edu.tasklist.web.dto.mapper;

import by.edu.tasklist.domain.task.TaskImage;
import by.edu.tasklist.web.dto.task.TaskImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskImageMapper {

    TaskImage toEntity(TaskImageDto dto);
}
