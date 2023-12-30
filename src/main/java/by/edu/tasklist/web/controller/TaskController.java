package by.edu.tasklist.web.controller;

import by.edu.tasklist.domain.task.Task;
import by.edu.tasklist.domain.task.TaskImage;
import by.edu.tasklist.service.TaskService;
import by.edu.tasklist.web.dto.mapper.TaskImageMapper;
import by.edu.tasklist.web.dto.mapper.TaskMapper;
import by.edu.tasklist.web.dto.task.TaskDto;
import by.edu.tasklist.web.dto.task.TaskImageDto;
import by.edu.tasklist.web.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Controller", description = "Task API")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final TaskImageMapper taskImageMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get TaskDto by id")
    @PreAuthorize("canAccessTask(#id)")
    public TaskDto getById(@PathVariable long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task")
    @PreAuthorize("canAccessTask(#id)")
    public void deleteById(@PathVariable long id) {
        taskService.delete(id);
    }

    @PutMapping
    @Operation(summary = "Update task")
    @PreAuthorize("canAccessTask(#dto.id)")
    public TaskDto update(@Validated(OnUpdate.class)
                          @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @PostMapping("/{id}/image")
    @Operation(summary = "Upload image to task")
    @PreAuthorize("canAccessTask(#id)")
    public void uploadImage(@PathVariable Long id,
                            @Valid @ModelAttribute TaskImageDto imageDto) {
        TaskImage image = taskImageMapper.toEntity(imageDto);
        taskService.uploadImage(id, image);
    }
}
