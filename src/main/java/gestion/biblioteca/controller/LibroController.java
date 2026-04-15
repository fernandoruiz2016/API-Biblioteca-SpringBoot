package gestion.biblioteca.controller;

import gestion.biblioteca.dto.request.LibroRequestDto;
import gestion.biblioteca.dto.response.ApiResponse;
import gestion.biblioteca.dto.response.LibroResponseDto;
import gestion.biblioteca.service.impl.LibroService;
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
@RequestMapping("/libros")
@Tag(name = "Libros", description = "Endpoints para la gestión del catálogo de libros")
public class LibroController {
    private final LibroService libroService;

    @GetMapping
    @Operation(summary = "Listar todos los libros",
            description = "Retorna una lista de todos los libros que se encuentran con estado activo (1)")
    public ResponseEntity<ApiResponse<List<LibroResponseDto>>> findAll()
    {
        return ResponseEntity.ok(ApiResponse.ok( libroService.findAll() ));
    }

    @GetMapping("/{idLibro}")
    @Operation(summary = "Obtener un libro por ID",
            description = "Busca un libro específico en la base de datos mediante su identificador único")
    public ResponseEntity< ApiResponse<LibroResponseDto> > findById(@PathVariable Long idLibro)
    {
        return ResponseEntity.ok(ApiResponse.ok(libroService.findById(idLibro)));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo libro",
            description = "Crea un libro en el sistema. El stock inicial debe ser mayor a cero.")
    public ResponseEntity<ApiResponse<LibroResponseDto>> create(@Valid @RequestBody LibroRequestDto LibroRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( ApiResponse.ok("Se ha creado el libro",   libroService.create(LibroRequestDto)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un libro existente",
            description = "Modifica los datos de un libro ya registrado. Se requiere el ID en la URL y el cuerpo del JSON.")
    public ResponseEntity<ApiResponse<LibroResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody LibroRequestDto request) {
        LibroResponseDto libroActualizado = libroService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Libro actualizado correctamente", libroActualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un libro",
            description = "Cambia el estado del libro a 0 para que no aparezca en las búsquedas.")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        libroService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Libro eliminado correctamente", null));
    }
}
