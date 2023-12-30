package by.edu.tasklist.service;

import by.edu.tasklist.web.dto.auth.JwtRequest;
import by.edu.tasklist.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}
