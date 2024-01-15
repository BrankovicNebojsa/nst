package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.AcademicTitleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademicTitleService {

    AcademicTitleDto save(AcademicTitleDto academicTitleDto) throws Exception;

    List<AcademicTitleDto> getAll();

    List<AcademicTitleDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    AcademicTitleDto update(Long id, AcademicTitleDto academicTitleDto) throws Exception;

    AcademicTitleDto findById(Long id) throws Exception;
}
