package gestion.biblioteca.service.impl;

import gestion.biblioteca.entity.Libro;
import gestion.biblioteca.entity.Prestamo;
import gestion.biblioteca.entity.Usuario;
import gestion.biblioteca.repository.LibroRepository;
import gestion.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.PrestamoRequestDto;
import gestion.biblioteca.dto.response.PrestamoResponseDto;
import gestion.biblioteca.mapper.PrestamoMapper;
import gestion.biblioteca.repository.PrestamoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoMapper prestamoMapper;
    private final PrestamoRepository prestamoRepository;

    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional (readOnly = true)
    public List<PrestamoResponseDto> findAll() {
        return prestamoRepository.findAll().stream()
                .filter(u -> u.getEstado() == 1)
                .map(prestamoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional (readOnly = true)
    public PrestamoResponseDto findById(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById((long) Integer.parseInt(idPrestamo.toString()))
                .orElseThrow( () -> new RuntimeException("Error al buscar el prestamo"));
        return prestamoMapper.toResponse(prestamo);
    }

    @Override
    @Transactional
    public PrestamoResponseDto create(PrestamoRequestDto prestamoRequestDto) {
        log.info("Create Prestamo: {}", prestamoRequestDto);

        Libro libro = libroRepository.findById(prestamoRequestDto.idLibro())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Usuario usuario = usuarioRepository.findById(prestamoRequestDto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (libro.getStock() <= 0) {
            throw new RuntimeException("No hay stock disponible");
        }

        Prestamo prestamo = prestamoMapper.toEntity(prestamoRequestDto);
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);

        libro.setStock(libro.getStock() - 1);

        return prestamoMapper.toResponse(prestamoRepository.save(prestamo));
    }

    @Override
    @Transactional
    public PrestamoResponseDto update(Long idPrestamo, PrestamoRequestDto prestamoRequestDto) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Error al buscar el prestamo"));

        prestamoMapper.updateFromRequest(prestamoRequestDto, prestamo);

        Usuario usuario = usuarioRepository.findById(prestamoRequestDto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Libro libro = libroRepository.findById(prestamoRequestDto.idLibro())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);

        String userLogueado = obtenerUsuarioLogueado();
        log.info("Actualizando préstamo {} por usuario: {}", idPrestamo, userLogueado);

        prestamo.setUsuarioModificacion(userLogueado);
        prestamo.setFechaModificacion(LocalDateTime.now());
        prestamo.setIpModificacion("127.0.0.1");

        Prestamo actualizado = prestamoRepository.saveAndFlush(prestamo);

        return prestamoMapper.toResponse(actualizado);
    }

    @Override
    @Transactional
    public void delete(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Error al buscar el prestamo"));
        prestamo.setEstado(0);

        prestamo.setUsuarioModificacion(obtenerUsuarioLogueado());
        prestamo.setFechaModificacion(LocalDateTime.now());
        prestamo.setIpModificacion("127.0.0.1");

        prestamoRepository.save(prestamo);

        log.info("Prestamo con ID {} desactivado", idPrestamo);
    }

    private String obtenerUsuarioLogueado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "SYSTEM_ANONYMOUS";
    }
}
