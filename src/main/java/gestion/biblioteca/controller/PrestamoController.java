package gestion.biblioteca.controller;

import gestion.biblioteca.dto.request.PrestamoRequestDto;
import gestion.biblioteca.dto.response.ApiResponse;
import gestion.biblioteca.dto.response.PrestamoResponseDto;
import gestion.biblioteca.service.impl.PrestamoService;
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
@RequestMapping("/prestamos")
@Tag(name = "Préstamos", description = "Operaciones relacionadas con el alquiler y devolución de libros")
public class PrestamoController {
    private final PrestamoService prestamoService;

    @GetMapping
    @Operation(summary = "Listar todos los préstamos",
            description = "Obtiene una lista de todos los préstamos registrados con estado activo.")
    public ResponseEntity<ApiResponse<List<PrestamoResponseDto>>> findAll()
    {
        return ResponseEntity.ok(ApiResponse.ok( prestamoService.findAll() ));
    }

    @GetMapping("/{idPrestamo}")
    @Operation(summary = "Consultar un préstamo por ID",
            description = "Devuelve la información detallada de un préstamo, incluyendo el libro y el usuario asociado.")
    public ResponseEntity< ApiResponse<PrestamoResponseDto> > findById(@PathVariable Long idPrestamo)
    {
        return ResponseEntity.ok(ApiResponse.ok(prestamoService.findById(idPrestamo)));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo préstamo",
            description = "Crea un registro de préstamo.")
    public ResponseEntity<ApiResponse<PrestamoResponseDto>> create(@Valid @RequestBody PrestamoRequestDto PrestamoRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( ApiResponse.ok("Se ha creado el prestamo",   prestamoService.create(PrestamoRequestDto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un préstamo",
            description = "Permite modificar fechas o cambiar el libro/usuario de un préstamo existente.")
    public ResponseEntity<ApiResponse<PrestamoResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody PrestamoRequestDto request) {
        PrestamoResponseDto prestamoActualizado = prestamoService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Prestamo actualizado correctamente", prestamoActualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Anular un préstamo",
            description = "Realiza una eliminación lógica del préstamo en el sistema.")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        prestamoService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Prestamo eliminado correctamente", null));
    }

    @PatchMapping("/{id}/devolver")
    @Operation(summary = "Procesar devolución",
            description = "Finaliza un préstamo activo, cambia su estado a 1 y reintegra el libro al stock.")
    public ResponseEntity<ApiResponse<Void>> devolver(@PathVariable Long id) {
        prestamoService.devolver(id);
        return ResponseEntity.ok(ApiResponse.ok("Libro devuelto correctamente y stock actualizado", null));
    }
}
