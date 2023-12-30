package by.edu.tasklist.web.dto.mapper;

import by.edu.tasklist.domain.user.User;
import by.edu.tasklist.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
