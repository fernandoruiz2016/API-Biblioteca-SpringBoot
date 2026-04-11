package gestion.biblioteca.service.impl;

import gestion.biblioteca.dto.request.AfpRequestDto;
import gestion.biblioteca.dto.response.AfpResponseDto;

import java.util.List;

public interface AfpService {
    List<AfpResponseDto> findAll();
    AfpResponseDto findById(Long idAfp);
    AfpResponseDto create(AfpRequestDto afpRequestDto);
    AfpResponseDto update(Long idAfp, AfpRequestDto afpRequestDto);
    void delete(Long idAfp);
}
