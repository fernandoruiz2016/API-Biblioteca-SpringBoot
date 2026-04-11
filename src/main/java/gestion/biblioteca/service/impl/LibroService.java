package gestion.biblioteca.service.impl;

import gestion.biblioteca.dto.request.LibroRequestDto;
import gestion.biblioteca.dto.response.LibroResponseDto;

import java.util.List;

public interface LibroService {
    List<LibroResponseDto> findAll();
    LibroResponseDto findById(Long idLibro);
    LibroResponseDto create(LibroRequestDto libroRequestDto);
    LibroResponseDto update(Long idLibro, LibroRequestDto libroRequestDto);
    void delete(Long idLibro);
}
