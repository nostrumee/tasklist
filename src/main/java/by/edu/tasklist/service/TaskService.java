package by.edu.tasklist.service;

import by.edu.tasklist.domain.task.Task;
import by.edu.tasklist.domain.task.TaskImage;

import java.util.List;

public interface TaskService {

    Task getById(long id);

    List<Task> getAllByUserId(long userId);

    Task update(Task task);

    Task create(Task task, long userId);

    void delete(long id);

    void uploadImage(long id, TaskImage image);
}
