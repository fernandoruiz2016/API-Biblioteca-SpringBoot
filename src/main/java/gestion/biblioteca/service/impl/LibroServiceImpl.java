package gestion.biblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.LibroRequestDto;
import gestion.biblioteca.dto.response.LibroResponseDto;
import gestion.biblioteca.entity.Libro;
import gestion.biblioteca.mapper.LibroMapper;
import gestion.biblioteca.repository.LibroRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {
    private final LibroMapper libroMapper;
    private final LibroRepository libroRepository;

    @Override
    @Transactional (readOnly = true)
    public List<LibroResponseDto> findAll() {
        return libroMapper.toResponseList(libroRepository.findAll());
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
        Libro libro = libroRepository.findById((long) Integer.parseInt(idLibro.toString()))
                .orElseThrow(() -> new RuntimeException("Error al buscar el libro"));
        libroMapper.updateFromRequest(libroRequestDto, libro);
        return libroMapper.toResponse(libroRepository.save(libro));
    }

    @Override
    @Transactional
    public void delete(Long idLibro) {
        if(!libroRepository.existsById((long) Integer.parseInt(idLibro.toString()))) {
            throw new RuntimeException("No existe el id del libro");
        }
        libroRepository.deleteById((long) Integer.parseInt(idLibro.toString()));
    }
}
