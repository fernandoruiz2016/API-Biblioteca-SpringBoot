package gestion.biblioteca.controller;

import gestion.biblioteca.dto.request.UsuarioRequestDto;
import gestion.biblioteca.dto.response.ApiResponse;
import gestion.biblioteca.dto.response.UsuarioResponseDto;
import gestion.biblioteca.service.impl.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDto>>> findAll()
    {
        return ResponseEntity.ok(ApiResponse.ok( usuarioService.findAll() ));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity< ApiResponse<UsuarioResponseDto> > findById(@PathVariable Long idUsuario)
    {
        return ResponseEntity.ok(ApiResponse.ok(usuarioService.findById(idUsuario)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponseDto>> create(@Valid @RequestBody UsuarioRequestDto UsuarioRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( ApiResponse.ok("Se ha creado el usuario",   usuarioService.create(UsuarioRequestDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDto request) {
        UsuarioResponseDto usuarioActualizado = usuarioService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Usuario actualizado correctamente", usuarioActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Usuario eliminado correctamente", null));
    }
}
