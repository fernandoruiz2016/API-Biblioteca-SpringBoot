package gestion.biblioteca.controller;

import gestion.biblioteca.dto.request.RolRequestDto;
import gestion.biblioteca.dto.response.ApiResponse;
import gestion.biblioteca.dto.response.RolResponseDto;
import gestion.biblioteca.service.impl.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/roles")
@Tag(name = "Roles", description = "Gestión de perfiles de usuario y permisos del sistema")
public class RolController {
    private final RolService rolService;

    @GetMapping
    @Operation(summary = "Listar todos los roles",
            description = "Obtiene la lista de roles configurados en el sistema")
    public ResponseEntity<ApiResponse<List<RolResponseDto>>> findAll()
    {
        return ResponseEntity.ok(ApiResponse.ok( rolService.findAll() ));
    }

    @GetMapping("/{idRol}")
    @Operation(summary = "Buscar rol por ID",
            description = "Recupera los detalles de un rol específico mediante su identificador único.")
    public ResponseEntity< ApiResponse<RolResponseDto> > findById(@PathVariable Long idRol)
    {
        return ResponseEntity.ok(ApiResponse.ok(rolService.findById(idRol)));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo rol",
            description = "Registra un nuevo perfil en la base de datos.")
    public ResponseEntity<ApiResponse<RolResponseDto>> create(@Valid @RequestBody RolRequestDto RolRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( ApiResponse.ok("Se ha creado el rol",   rolService.create(RolRequestDto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un rol",
            description = "Modifica la descripción o el nombre de un rol existente.")
    public ResponseEntity<ApiResponse<RolResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody RolRequestDto request) {
        RolResponseDto rolActualizado = rolService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Rol actualizado correctamente", rolActualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un rol",
            description = "Realiza la eliminación del rol.")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        rolService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Rol eliminado correctamente", null));
    }
}

