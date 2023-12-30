package by.edu.tasklist.service.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
public record JwtProperties(
        String secret,
        long access,
        long refresh
) {
}
