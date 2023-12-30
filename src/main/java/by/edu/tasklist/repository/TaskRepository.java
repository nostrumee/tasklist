package by.edu.tasklist.repository;

import by.edu.tasklist.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static by.edu.tasklist.util.query.TaskQueries.FIND_ALL_BY_USER_ID_QUERY;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = FIND_ALL_BY_USER_ID_QUERY, nativeQuery = true)
    List<Task> findAllByUserId(@Param("userId") Long userId);
}
