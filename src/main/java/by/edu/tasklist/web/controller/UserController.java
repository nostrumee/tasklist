package by.edu.tasklist.web.controller;

import by.edu.tasklist.domain.task.Task;
import by.edu.tasklist.domain.user.User;
import by.edu.tasklist.service.TaskService;
import by.edu.tasklist.service.UserService;
import by.edu.tasklist.web.dto.mapper.TaskMapper;
import by.edu.tasklist.web.dto.mapper.UserMapper;
import by.edu.tasklist.web.dto.task.TaskDto;
import by.edu.tasklist.web.dto.user.UserDto;
import by.edu.tasklist.web.validation.OnCreate;
import by.edu.tasklist.web.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @PutMapping
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public UserDto update(@Validated(OnUpdate.class)
                          @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get UserDto by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getById(@PathVariable long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteById(@PathVariable long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get all User tasks")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public List<TaskDto> getTasksByUserId(@PathVariable long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "Add task to user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public TaskDto createTask(@PathVariable long id,
                              @Validated(OnCreate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }
}
