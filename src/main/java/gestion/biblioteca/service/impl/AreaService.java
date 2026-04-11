package gestion.biblioteca.service.impl;

import gestion.biblioteca.dto.request.AreaRequestDto;
import gestion.biblioteca.dto.response.AreaResponseDto;

import java.util.List;

public interface AreaService {
    List<AreaResponseDto> findAll();
    AreaResponseDto findById(Long idArea);
    AreaResponseDto create(AreaRequestDto areaRequestDto);
    AreaResponseDto update(Long idArea, AreaRequestDto areaRequestDto);
    void delete(Long idArea);
}
