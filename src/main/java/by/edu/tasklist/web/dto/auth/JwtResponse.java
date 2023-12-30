package by.edu.tasklist.web.dto.auth;

import lombok.Builder;

@Builder
public record JwtResponse(
        Long id,
        String username,
        String accessToken,
        String refreshToken
) {
}
