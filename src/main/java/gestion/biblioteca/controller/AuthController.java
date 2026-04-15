package gestion.biblioteca.controller;

import gestion.biblioteca.dto.auth.LoginRequest;
import gestion.biblioteca.dto.auth.LoginResponse;
import gestion.biblioteca.dto.auth.RegisterRequest;
import gestion.biblioteca.dto.response.ApiResponse;
import gestion.biblioteca.dto.response.UsuarioResponseDto;
import gestion.biblioteca.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticación", description = "Endpoints para el control de acceso y registro de nuevos usuarios")
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión y obtener Token",
            description = "Valida las credenciales del usuario y retorna un token JWT.")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(ApiResponse.ok(authService.login(loginRequest)));
    }

    @PostMapping("/register")
    @Operation(summary = "Registro público de usuarios",
            description = "Permite a los nuevos usuarios crear una cuenta.")
    public ResponseEntity<ApiResponse<UsuarioResponseDto>> register(@Valid @RequestBody RegisterRequest registerRequest)
    {
        return ResponseEntity.ok(ApiResponse.ok("Usuario registrado exitosamente", authService.register(registerRequest)));
    }
}