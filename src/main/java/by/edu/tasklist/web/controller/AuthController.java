package by.edu.tasklist.web.controller;

import by.edu.tasklist.domain.user.User;
import by.edu.tasklist.service.AuthService;
import by.edu.tasklist.service.UserService;
import by.edu.tasklist.web.dto.auth.JwtRequest;
import by.edu.tasklist.web.dto.auth.JwtResponse;
import by.edu.tasklist.web.dto.mapper.UserMapper;
import by.edu.tasklist.web.dto.user.UserDto;
import by.edu.tasklist.web.validation.OnCreate;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
