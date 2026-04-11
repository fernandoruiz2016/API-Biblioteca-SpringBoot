package gestion.biblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.AreaRequestDto;
import gestion.biblioteca.dto.response.AreaResponseDto;
import gestion.biblioteca.entity.Area;
import gestion.biblioteca.mapper.AreaMapper;
import gestion.biblioteca.repository.AreaRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final AreaMapper areaMapper;
    private final AreaRepository areaRepository;

    @Override
    @Transactional
    public List<AreaResponseDto> findAll() {
        return areaMapper.toResponseList(areaRepository.findAll());
    }

    @Override
    @Transactional (readOnly = true)
    public AreaResponseDto findById(Long idArea) {
        Area area = areaRepository.findById((long) Integer.parseInt(idArea.toString()))
                .orElseThrow( () -> new RuntimeException("Error al buscar la AFP"));
        return areaMapper.toResponse(area);
    }

    @Override
    @Transactional
    public AreaResponseDto create(AreaRequestDto areaRequestDto) {
        log.info("Create AFP: {}", areaRequestDto);
        return areaMapper.toResponse(areaRepository.save(areaMapper.toEntity(areaRequestDto)));
    }

    @Override
    @Transactional
    public AreaResponseDto update(Long idArea, AreaRequestDto areaRequestDto) {
        Area area = areaRepository.findById((long) Integer.parseInt(idArea.toString()))
                .orElseThrow(() -> new RuntimeException("AFP al buscar la AFP"));
        areaMapper.updateFromRequest(areaRequestDto, area);
        return areaMapper.toResponse(areaRepository.save(area));
    }

    @Override
    @Transactional
    public void delete(Long idArea) {
        if(!areaRepository.existsById((long) Integer.parseInt(idArea.toString()))) {
            throw new RuntimeException("No existe el id de la AFP");
        }
        areaRepository.deleteById((long) Integer.parseInt(idArea.toString()));
    }
}
