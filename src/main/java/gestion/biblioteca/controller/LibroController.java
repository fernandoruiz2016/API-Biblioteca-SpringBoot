package gestion.biblioteca.controller;

import gestion.biblioteca.dto.request.LibroRequestDto;
import gestion.biblioteca.dto.response.ApiResponse;
import gestion.biblioteca.dto.response.LibroResponseDto;
import gestion.biblioteca.service.impl.LibroService;
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
@RequestMapping("/libros")
public class LibroController {
    private final LibroService libroService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<LibroResponseDto>>> findAll()
    {
        return ResponseEntity.ok(ApiResponse.ok( libroService.findAll() ));
    }

    @GetMapping("/{idLibro}")
    public ResponseEntity< ApiResponse<LibroResponseDto> > findById(@PathVariable Long idLibro)
    {
        return ResponseEntity.ok(ApiResponse.ok(libroService.findById(idLibro)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LibroResponseDto>> create(@Valid @RequestBody LibroRequestDto LibroRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( ApiResponse.ok("Se ha creado el libro",   libroService.create(LibroRequestDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LibroResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody LibroRequestDto request) {
        LibroResponseDto libroActualizado = libroService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Libro actualizado correctamente", libroActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        libroService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Libro eliminado correctamente", null));
    }
}
