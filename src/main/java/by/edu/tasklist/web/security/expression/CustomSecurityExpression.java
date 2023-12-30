package by.edu.tasklist.web.security.expression;

import by.edu.tasklist.domain.user.Role;
import by.edu.tasklist.service.UserService;
import by.edu.tasklist.web.security.JwtEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("customSecurityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpression {

    private final UserService userService;

    public boolean canAccessUser(long id) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        return userId.equals(id) || hasAnyRole(authentication, Role.ROLE_ADMIN);
    }

    public boolean canAccessTask(long taskId) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long id = user.getId();

        return userService.isTaskOwner(id, taskId);
    }

    private boolean hasAnyRole(Authentication authentication, Role... roles) {
        for (Role role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());

            if (authentication.getAuthorities().contains(authority)) {
                return true;
            }
        }

        return false;
    }
}
