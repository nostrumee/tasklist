package by.edu.tasklist.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request for login")
public record JwtRequest(

        @Schema(description = "email", example = "johndoe@gmail.com")
        @NotNull(message = "Username must not be null.")
        String username,

        @Schema(description = "password", example = "12345")
        @NotNull(message = "Password must not be null.")
        String password
) {
}
