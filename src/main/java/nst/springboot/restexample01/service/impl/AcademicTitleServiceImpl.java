package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.converter.impl.AcademicTitleConverter;
import nst.springboot.restexample01.domain.AcademicTitle;
import nst.springboot.restexample01.dto.AcademicTitleDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.repository.AcademicTitleRepository;
import nst.springboot.restexample01.service.AcademicTitleService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademicTitleServiceImpl implements AcademicTitleService {
    private final AcademicTitleRepository academicTitleRepository;
    private final AcademicTitleConverter academicTitleConverter;

    public AcademicTitleServiceImpl(AcademicTitleRepository academicTitleRepository, AcademicTitleConverter academicTitleConverter) {
        this.academicTitleRepository = academicTitleRepository;
        this.academicTitleConverter = academicTitleConverter;
    }

    @Override
    public AcademicTitleDto save(AcademicTitleDto academicTitleDto) throws Exception {
        Optional<AcademicTitle> academicTitleOptional = academicTitleRepository.findByName(academicTitleDto.getName());
        if (academicTitleOptional.isPresent()) {
            throw new EntityAlreadyExistsException("Akademska titula sa tim nazivom vec postoji!");
        } else {
            AcademicTitle academicTitle = academicTitleConverter.toEntity(academicTitleDto);
            academicTitle = academicTitleRepository.save(academicTitle);
            return academicTitleConverter.toDto(academicTitle);
        }
    }

    @Override
    public List<AcademicTitleDto> getAll() {
        return academicTitleRepository
                .findAll()
                .stream().map(academicTitleConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AcademicTitleDto> getAll(Pageable pageable) {
        return academicTitleRepository
                .findAll(pageable).getContent()
                .stream().map(academicTitleConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<AcademicTitle> academyTitle = academicTitleRepository.findById(id);
        if (academyTitle.isPresent()) {
            AcademicTitle academicTitle = academyTitle.get();
            academicTitleRepository.delete(academicTitle);
        } else {
            throw new Exception("Academic title does not exist!");
        }
    }

    @Transactional
    @Override
    public AcademicTitleDto update(Long id, AcademicTitleDto academicTitleDto) throws Exception {
        Optional<AcademicTitle> optionalAcademicTitle = academicTitleRepository.findById(id);
        if (optionalAcademicTitle.isPresent()) {
            academicTitleDto.setId(optionalAcademicTitle.get().getId());
            return academicTitleConverter.toDto(academicTitleRepository.save(academicTitleConverter.toEntity(academicTitleDto)));
        }
        throw new Exception("Academic title with that id does not exist");
    }

    @Override
    public AcademicTitleDto findById(Long id) throws Exception {
        Optional<AcademicTitle> academyTitle = academicTitleRepository.findById(id);
        if (academyTitle.isPresent()) {
            AcademicTitle academicTitle = academyTitle.get();
            return academicTitleConverter.toDto(academicTitle);
        } else {
            throw new Exception("Academic title does not exist!");
        }
    }
}
