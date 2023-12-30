package by.edu.tasklist.web.mappers;

import by.edu.tasklist.domain.user.User;
import by.edu.tasklist.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
