package gestion.biblioteca.service.impl;

import gestion.biblioteca.entity.Rol;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.RolRequestDto;
import gestion.biblioteca.dto.response.RolResponseDto;
import gestion.biblioteca.mapper.RolMapper;
import gestion.biblioteca.repository.RolRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolMapper rolMapper;
    private final RolRepository rolRepository;

    @Override
    @Transactional (readOnly = true)
    public List<RolResponseDto> findAll() {
        return rolRepository.findAll().stream()
                //.filter(u -> u.getEstado() == 1)
                .map(rolMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional (readOnly = true)
    public RolResponseDto findById(Long idRol) {
        Rol rol = rolRepository.findById((long) Integer.parseInt(idRol.toString()))
                .orElseThrow( () -> new RuntimeException("Error al buscar el rol"));
        return rolMapper.toResponse(rol);
    }

    @Override
    @Transactional
    public RolResponseDto create(RolRequestDto rolRequestDto) {
        log.info("Create Rol: {}", rolRequestDto);
        return rolMapper.toResponse(rolRepository.save(rolMapper.toEntity(rolRequestDto)));
    }

    @Override
    @Transactional
    public RolResponseDto update(Long idRol, RolRequestDto rolRequestDto) {
        Rol rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Error al buscar el rol"));

        rolMapper.updateFromRequest(rolRequestDto, rol);

        rol.setUsuarioModificacion(obtenerUsuarioLogueado());
        rol.setFechaModificacion(LocalDateTime.now());
        rol.setIpModificacion("127.0.0.1");

        return rolMapper.toResponse(rolRepository.save(rol));
    }

    @Override
    @Transactional
    public void delete(Long idRol) {
        Rol rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Error al buscar el rol"));
        rol.setEstado(0);

        rol.setUsuarioModificacion(obtenerUsuarioLogueado());
        rol.setFechaModificacion(LocalDateTime.now());
        rol.setIpModificacion("127.0.0.1");

        rolRepository.save(rol);

        log.info("Rol con ID {} desactivado", idRol);
    }

    private String obtenerUsuarioLogueado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "SYSTEM_ANONYMOUS";
    }
}
