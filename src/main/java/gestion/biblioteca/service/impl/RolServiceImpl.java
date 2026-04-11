package gestion.biblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.RolRequestDto;
import gestion.biblioteca.dto.response.RolResponseDto;
import gestion.biblioteca.entity.Rol;
import gestion.biblioteca.mapper.RolMapper;
import gestion.biblioteca.repository.RolRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolMapper rolMapper;
    private final RolRepository rolRepository;

    @Override
    @Transactional (readOnly = true)
    public List<RolResponseDto> findAll() {
        return rolMapper.toResponseList(rolRepository.findAll());
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
        Rol rol = rolRepository.findById((long) Integer.parseInt(idRol.toString()))
                .orElseThrow(() -> new RuntimeException("Error al buscar el rol"));
        rolMapper.updateFromRequest(rolRequestDto, rol);
        return rolMapper.toResponse(rolRepository.save(rol));
    }

    @Override
    @Transactional
    public void delete(Long idRol) {
        if(!rolRepository.existsById((long) Integer.parseInt(idRol.toString()))) {
            throw new RuntimeException("No existe el id del rol");
        }
        rolRepository.deleteById((long) Integer.parseInt(idRol.toString()));
    }
}
