package by.edu.tasklist.util.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserQueries {

    public static final String IS_TASK_OWNER_QUERY = """
             SELECT exists(
                           SELECT 1
                           FROM users_tasks
                           WHERE user_id = :userId
                             AND task_id = :taskId)
            """;
}
