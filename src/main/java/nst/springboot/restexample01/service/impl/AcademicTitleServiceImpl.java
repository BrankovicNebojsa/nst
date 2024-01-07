package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.domain.AcademicTitle;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.repository.AcademicTitleRepository;
import nst.springboot.restexample01.service.AcademicTitleService;
import nst.springboot.restexample01.converter.impl.AcademicTitleConverter;
import nst.springboot.restexample01.dto.AcademicTitleDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AcademicTitleServiceImpl implements AcademicTitleService {

    private AcademicTitleRepository academicTitleRepository;
    private AcademicTitleConverter academicTitleConverter;

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
                .stream().map(entity -> academicTitleConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<AcademicTitleDto> getAll(Pageable pageable) {
        return academicTitleRepository
                .findAll(pageable).getContent()
                .stream().map(entity -> academicTitleConverter.toDto(entity))
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

    @Override
    public void update(AcademicTitleDto academicTitleDto) throws Exception {

    }

    @Override
    public AcademicTitleDto findById(Long id) throws Exception {
        Optional<AcademicTitle> academyTitle = academicTitleRepository.findById(id);
        if (academyTitle.isPresent()) {
            //postoji
            AcademicTitle academicTitle = academyTitle.get();
            return academicTitleConverter.toDto(academicTitle);
        } else {
            throw new Exception("Academic title does not exist!");
        }
    }
}
