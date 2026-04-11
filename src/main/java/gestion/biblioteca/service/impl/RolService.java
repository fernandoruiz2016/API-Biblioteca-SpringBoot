package gestion.biblioteca.service.impl;

import gestion.biblioteca.dto.request.RolRequestDto;
import gestion.biblioteca.dto.response.RolResponseDto;

import java.util.List;

public interface RolService {
    List<RolResponseDto> findAll();
    RolResponseDto findById(Long idRol);
    RolResponseDto create(RolRequestDto rolRequestDto);
    RolResponseDto update(Long idRol, RolRequestDto rolRequestDto);
    void delete(Long idRol);
}
