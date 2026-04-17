package gestion.biblioteca.service.impl;

import gestion.biblioteca.dto.request.PrestamoRequestDto;
import gestion.biblioteca.dto.response.PrestamoResponseDto;

import java.util.List;

public interface PrestamoService {
    List<PrestamoResponseDto> findAll();
    PrestamoResponseDto findById(Long idPrestamo);
    PrestamoResponseDto create(PrestamoRequestDto prestamoRequestDto);
    PrestamoResponseDto update(Long idPrestamo, PrestamoRequestDto prestamoRequestDto);
    void delete(Long idPrestamo);
    void devolver(Long idPrestamo);
}
