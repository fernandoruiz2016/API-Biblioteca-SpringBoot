package gestion.biblioteca.controller;

import gestion.biblioteca.dto.request.PrestamoRequestDto;
import gestion.biblioteca.dto.response.ApiResponse;
import gestion.biblioteca.dto.response.PrestamoResponseDto;
import gestion.biblioteca.service.impl.PrestamoService;
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
public class PrestamoController {
    private final PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PrestamoResponseDto>>> findAll()
    {
        return ResponseEntity.ok(ApiResponse.ok( prestamoService.findAll() ));
    }

    @GetMapping("/{idPrestamo}")
    public ResponseEntity< ApiResponse<PrestamoResponseDto> > findById(@PathVariable Long idPrestamo)
    {
        return ResponseEntity.ok(ApiResponse.ok(prestamoService.findById(idPrestamo)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PrestamoResponseDto>> create(@Valid @RequestBody PrestamoRequestDto PrestamoRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( ApiResponse.ok("Se ha creado el prestamo",   prestamoService.create(PrestamoRequestDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PrestamoResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody PrestamoRequestDto request) {
        PrestamoResponseDto prestamoActualizado = prestamoService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Prestamo actualizado correctamente", prestamoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        prestamoService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Prestamo eliminado correctamente", null));
    }
}
