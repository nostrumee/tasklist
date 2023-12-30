package by.edu.tasklist.service;

import by.edu.tasklist.domain.user.User;

public interface UserService {

    User getById(long id);

    User getByUsername(String username);

    User update(User user);

    User create(User user);

    boolean isTaskOwner(long userId, long taskId);

    void delete(long id);
}
