package gestion.biblioteca.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gestion.biblioteca.dto.request.AfpRequestDto;
import gestion.biblioteca.dto.response.AfpResponseDto;
import gestion.biblioteca.entity.Afp;
import gestion.biblioteca.mapper.AfpMapper;
import gestion.biblioteca.repository.AfpRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AfpServiceImpl implements AfpService {
    private final AfpMapper afpMapper;
    private final AfpRepository afpRepository;

    @Override
    @Transactional
    public List<AfpResponseDto> findAll() {
        return afpMapper.toResponseList(afpRepository.findAll());
    }

    @Override
    @Transactional (readOnly = true)
    public AfpResponseDto findById(Long idAfp) {
        Afp afp = afpRepository.findById((long) Integer.parseInt(idAfp.toString()))
                .orElseThrow( () -> new RuntimeException("Error al buscar la AFP"));
        return afpMapper.toResponse(afp);
    }

    @Override
    @Transactional
    public AfpResponseDto create(AfpRequestDto afpRequestDto) {
        log.info("Create AFP: {}", afpRequestDto);
        return afpMapper.toResponse(afpRepository.save(afpMapper.toEntity(afpRequestDto)));
    }

    @Override
    @Transactional
    public AfpResponseDto update(Long idAfp, AfpRequestDto afpRequestDto) {
        Afp afp = afpRepository.findById((long) Integer.parseInt(idAfp.toString()))
                .orElseThrow(() -> new RuntimeException("AFP al buscar la AFP"));
        afpMapper.updateFromRequest(afpRequestDto, afp);
        return afpMapper.toResponse(afpRepository.save(afp));
    }

    @Override
    @Transactional
    public void delete(Long idAfp) {
        if(!afpRepository.existsById((long) Integer.parseInt(idAfp.toString()))) {
            throw new RuntimeException("No existe el id de la AFP");
        }
        afpRepository.deleteById((long) Integer.parseInt(idAfp.toString()));
    }
}
