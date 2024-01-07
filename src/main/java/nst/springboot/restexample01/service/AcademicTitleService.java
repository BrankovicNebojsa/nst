package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.AcademicTitleDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AcademicTitleService {

    AcademicTitleDto save(AcademicTitleDto academicTitleDto) throws Exception;

    List<AcademicTitleDto> getAll();

    List<AcademicTitleDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    void update(AcademicTitleDto academicTitleDto) throws Exception;

    AcademicTitleDto findById(Long id) throws Exception;
}
