package gestion.biblioteca.service.impl;

import gestion.biblioteca.entity.Libro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.LibroRequestDto;
import gestion.biblioteca.dto.response.LibroResponseDto;
import gestion.biblioteca.entity.Libro;
import gestion.biblioteca.mapper.LibroMapper;
import gestion.biblioteca.repository.LibroRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {
    private final LibroMapper libroMapper;
    private final LibroRepository libroRepository;

    @Override
    @Transactional (readOnly = true)
    public List<LibroResponseDto> findAll() {
        return libroRepository.findAll().stream()
                .filter(u -> u.getEstado() == 1)
                .map(libroMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional (readOnly = true)
    public LibroResponseDto findById(Long idLibro) {
        Libro libro = libroRepository.findById((long) Integer.parseInt(idLibro.toString()))
                .orElseThrow( () -> new RuntimeException("Error al buscar el libro"));
        return libroMapper.toResponse(libro);
    }

    @Override
    @Transactional
    public LibroResponseDto create(LibroRequestDto libroRequestDto) {
        log.info("Create Libro: {}", libroRequestDto);
        return libroMapper.toResponse(libroRepository.save(libroMapper.toEntity(libroRequestDto)));
    }

    @Override
    @Transactional
    public LibroResponseDto update(Long idLibro, LibroRequestDto libroRequestDto) {
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Error al buscar el libro"));

        libroMapper.updateFromRequest(libroRequestDto, libro);

        libro.setUsuarioModificacion(obtenerUsuarioLogueado());
        libro.setFechaModificacion(LocalDateTime.now());
        libro.setIpModificacion("127.0.0.1");

        return libroMapper.toResponse(libroRepository.save(libro));
    }

    @Override
    @Transactional
    public void delete(Long idLibro) {
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RuntimeException("Error al buscar el libro"));
        libro.setEstado(0);

        libro.setUsuarioModificacion(obtenerUsuarioLogueado());
        libro.setFechaModificacion(LocalDateTime.now());
        libro.setIpModificacion("127.0.0.1");

        libroRepository.save(libro);

        log.info("Libro con ID {} desactivado", idLibro);
    }

    private String obtenerUsuarioLogueado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "SYSTEM_ANONYMOUS";
    }
}
