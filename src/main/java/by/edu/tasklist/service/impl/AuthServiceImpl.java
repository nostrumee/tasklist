package by.edu.tasklist.service.impl;

import by.edu.tasklist.domain.user.User;
import by.edu.tasklist.service.AuthService;
import by.edu.tasklist.service.UserService;
import by.edu.tasklist.web.dto.auth.JwtRequest;
import by.edu.tasklist.web.dto.auth.JwtResponse;
import by.edu.tasklist.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password()
        ));
        User user = userService.getByUsername(loginRequest.username());
        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername());

        return JwtResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
