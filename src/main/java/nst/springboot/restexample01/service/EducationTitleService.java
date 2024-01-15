package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.EducationTitleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EducationTitleService {

    EducationTitleDto save(EducationTitleDto educationTitleDto) throws Exception;

    List<EducationTitleDto> getAll();

    List<EducationTitleDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    EducationTitleDto update(Long id, EducationTitleDto educationTitleDto) throws Exception;

    EducationTitleDto findById(Long id) throws Exception;
}
