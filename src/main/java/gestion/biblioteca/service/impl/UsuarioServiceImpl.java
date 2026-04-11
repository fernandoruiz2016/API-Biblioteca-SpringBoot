package gestion.biblioteca.service.impl;

import gestion.biblioteca.entity.Rol;
import gestion.biblioteca.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.UsuarioRequestDto;
import gestion.biblioteca.dto.response.UsuarioResponseDto;
import gestion.biblioteca.entity.Usuario;
import gestion.biblioteca.mapper.UsuarioMapper;
import gestion.biblioteca.repository.UsuarioRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    @Transactional (readOnly = true)
    public List<UsuarioResponseDto> findAll() {
        return usuarioMapper.toResponseList(usuarioRepository.findAll());
    }

    @Override
    @Transactional (readOnly = true)
    public UsuarioResponseDto findById(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById((long) Integer.parseInt(idUsuario.toString()))
                .orElseThrow( () -> new RuntimeException("Error al buscar el usuario"));
        return usuarioMapper.toResponse(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDto create(UsuarioRequestDto usuarioRequestDto) {
        log.info("Create Usuario: {}", usuarioRequestDto);
        Rol rol = rolRepository.findById(usuarioRequestDto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Error al buscar el Rol"));

        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDto);
        usuario.setRol(rol);

        log.info("Creando el usuario: {}", usuario.getIdUsuario());

        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public UsuarioResponseDto update(Long idUsuario, UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRepository.findById((long) Integer.parseInt(idUsuario.toString()))
                .orElseThrow(() -> new RuntimeException("Error al buscar el usuario"));
        usuarioMapper.updateFromRequest(usuarioRequestDto, usuario);
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public void delete(Long idUsuario) {
        if(!usuarioRepository.existsById((long) Integer.parseInt(idUsuario.toString()))) {
            throw new RuntimeException("No existe el id del usuario");
        }
        usuarioRepository.deleteById((long) Integer.parseInt(idUsuario.toString()));
    }
}
