package by.edu.tasklist.service.impl;

import by.edu.tasklist.domain.exception.ResourceNotFoundException;
import by.edu.tasklist.domain.task.Status;
import by.edu.tasklist.domain.task.Task;
import by.edu.tasklist.domain.task.TaskImage;
import by.edu.tasklist.domain.user.User;
import by.edu.tasklist.repository.TaskRepository;
import by.edu.tasklist.service.ImageService;
import by.edu.tasklist.service.TaskService;
import by.edu.tasklist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final UserService userService;
    private final ImageService imageService;
    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "TaskService::getById", key = "#id")
    public Task getById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(long id) {
        return taskRepository.findAllByUserId(id);
    }

    @Override
    @Transactional
    @CachePut(value = "TaskService::getById", key = "#task.id")
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }
        taskRepository.save(task);

        return task;
    }

    @Override
    @Transactional
    @Cacheable(value = "TaskService::getById", key = "#task.id")
    public Task create(Task task, long userId) {
        User user = userService.getById(userId);
        task.setStatus(Status.TODO);
        user.getTasks().add(task);
        userService.update(user);

        return task;
    }

    @Override
    @Transactional
    @CacheEvict(value = "TaskService::getById", key = "#id")
    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "TaskService::getById", key = "#id")
    public void uploadImage(long id, TaskImage image) {
        Task task = getById(id);
        String fileName = imageService.upload(image);
        task.getImages().add(fileName);
        taskRepository.save(task);
    }
}
