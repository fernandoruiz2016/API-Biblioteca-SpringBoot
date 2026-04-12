package gestion.biblioteca.service.impl;

import gestion.biblioteca.entity.Libro;
import gestion.biblioteca.entity.Usuario;
import gestion.biblioteca.repository.LibroRepository;
import gestion.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.PrestamoRequestDto;
import gestion.biblioteca.dto.response.PrestamoResponseDto;
import gestion.biblioteca.entity.Prestamo;
import gestion.biblioteca.mapper.PrestamoMapper;
import gestion.biblioteca.repository.PrestamoRepository;

import java.util.List;

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
        return prestamoMapper.toResponseList(prestamoRepository.findAll());
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
        prestamo.setUsuarioCreacion("SISTEMA"); // Temporal hasta tener JWT
        prestamo.setIpCreacion("127.0.0.1");

        return prestamoMapper.toResponse(prestamoRepository.save(prestamo));
    }

    @Override
    @Transactional
    public PrestamoResponseDto update(Long idPrestamo, PrestamoRequestDto prestamoRequestDto) {
        Prestamo prestamo = prestamoRepository.findById((long) Integer.parseInt(idPrestamo.toString()))
                .orElseThrow(() -> new RuntimeException("Error al buscar el prestamo"));
        prestamoMapper.updateFromRequest(prestamoRequestDto, prestamo);
        return prestamoMapper.toResponse(prestamoRepository.save(prestamo));
    }

    @Override
    @Transactional
    public void delete(Long idPrestamo) {
        if(!prestamoRepository.existsById((long) Integer.parseInt(idPrestamo.toString()))) {
            throw new RuntimeException("No existe el id del prestamo");
        }
        prestamoRepository.deleteById((long) Integer.parseInt(idPrestamo.toString()));
    }
}
