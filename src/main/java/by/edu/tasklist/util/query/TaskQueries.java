package by.edu.tasklist.util.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TaskQueries {

    public static final String FIND_ALL_BY_USER_ID_QUERY = """
            SELECT * FROM tasks t
            JOIN users_tasks ut ON ut.task_id = t.id
            WHERE ut.user_id = :userId
            """;
}
