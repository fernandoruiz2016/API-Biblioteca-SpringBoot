package gestion.biblioteca.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gestion.biblioteca.dto.request.AfpRequestDto;
import gestion.biblioteca.dto.response.AfpResponseDto;
import gestion.biblioteca.service.impl.AfpService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/afps")
public class AfpController {
    private final AfpService afpService;

    @GetMapping
    public ResponseEntity<List<AfpResponseDto>> findAll() {
        return ResponseEntity.ok(afpService.findAll());
    }

    @GetMapping("/{idAfp}")
    public ResponseEntity<AfpResponseDto> findById(@PathVariable Long idAfp) {
        return ResponseEntity.ok(afpService.findById(idAfp));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AfpRequestDto afpRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(afpService.create(afpRequestDto));
    }
}
