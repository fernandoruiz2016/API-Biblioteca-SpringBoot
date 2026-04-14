package gestion.biblioteca.controller;

import gestion.biblioteca.dto.auth.LoginRequest;
import gestion.biblioteca.dto.auth.LoginResponse;
import gestion.biblioteca.dto.auth.RegisterRequest;
import gestion.biblioteca.dto.response.ApiResponse;
import gestion.biblioteca.dto.response.UsuarioResponseDto;
import gestion.biblioteca.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(ApiResponse.ok(authService.login(loginRequest)));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UsuarioResponseDto>> register(@Valid @RequestBody RegisterRequest registerRequest)
    {
        return ResponseEntity.ok(ApiResponse.ok("Usuario registrado exitosamente", authService.register(registerRequest)));
    }
}