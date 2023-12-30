package by.edu.tasklist.repository;

import by.edu.tasklist.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import static by.edu.tasklist.util.query.UserQueries.IS_TASK_OWNER_QUERY;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = IS_TASK_OWNER_QUERY, nativeQuery = true)
    boolean isTaskOwner(@Param("userId") Long userId, @Param("taskId") Long taskId);

}
