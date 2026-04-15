package gestion.biblioteca.service.impl;

import gestion.biblioteca.dto.auth.RegisterRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.UsuarioRequestDto;
import gestion.biblioteca.dto.response.UsuarioResponseDto;
import gestion.biblioteca.entity.Usuario;
import gestion.biblioteca.mapper.UsuarioMapper;
import gestion.biblioteca.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final AuthServiceImpl authService;

    @Override
    @Transactional (readOnly = true)
    public List<UsuarioResponseDto> findAll() {
        return usuarioRepository.findAll().stream()
                //.filter(u -> u.getEstado() == 1)
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toList());
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
        RegisterRequest registerRequest = new RegisterRequest(
                usuarioRequestDto.username(),
                usuarioRequestDto.password(),
                usuarioRequestDto.email(),
                usuarioRequestDto.roles()
        );

        // Usamos la lógica que ya funciona en AuthService
        return authService.register(registerRequest);
    }

    @Override
    @Transactional
    public UsuarioResponseDto update(Long idUsuario, UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Error al buscar el usuario"));

        usuarioMapper.updateFromRequest(usuarioRequestDto, usuario);

        usuario.setUsuarioModificacion(obtenerUsuarioLogueado());
        usuario.setFechaModificacion(LocalDateTime.now());
        usuario.setIpModificacion("127.0.0.1");

        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public void delete(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Error al buscar el usuario"));
        usuario.setEstado(0);

        usuario.setUsuarioModificacion(obtenerUsuarioLogueado());
        usuario.setFechaModificacion(LocalDateTime.now());
        usuario.setIpModificacion("127.0.0.1");

        usuarioRepository.save(usuario);

        log.info("Usuario con ID {} desactivado", idUsuario);
    }

    private String obtenerUsuarioLogueado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "SYSTEM_ANONYMOUS";
    }
}
