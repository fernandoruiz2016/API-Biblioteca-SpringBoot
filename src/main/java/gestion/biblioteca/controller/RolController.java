package gestion.biblioteca.controller;

import gestion.biblioteca.dto.request.RolRequestDto;
import gestion.biblioteca.dto.response.ApiResponse;
import gestion.biblioteca.dto.response.RolResponseDto;
import gestion.biblioteca.service.impl.RolService;
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
@RequestMapping("/rols")
public class RolController {
    private final RolService rolService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RolResponseDto>>> findAll()
    {
        return ResponseEntity.ok(ApiResponse.ok( rolService.findAll() ));
    }

    @GetMapping("/{idRol}")
    public ResponseEntity< ApiResponse<RolResponseDto> > findById(@PathVariable Long idRol)
    {
        return ResponseEntity.ok(ApiResponse.ok(rolService.findById(idRol)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RolResponseDto>> create(@Valid @RequestBody RolRequestDto RolRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( ApiResponse.ok("Se ha creado el rol",   rolService.create(RolRequestDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RolResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody RolRequestDto request) {
        RolResponseDto rolActualizado = rolService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Rol actualizado correctamente", rolActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        rolService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Rol eliminado correctamente", null));
    }
}

