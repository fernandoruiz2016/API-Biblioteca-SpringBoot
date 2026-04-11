package gestion.biblioteca.service.impl;

import gestion.biblioteca.dto.request.UsuarioRequestDto;
import gestion.biblioteca.dto.response.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDto> findAll();
    UsuarioResponseDto findById(Long idUsuario);
    UsuarioResponseDto create(UsuarioRequestDto usuarioRequestDto);
    UsuarioResponseDto update(Long idUsuario, UsuarioRequestDto usuarioRequestDto);
    void delete(Long idUsuario);
}
