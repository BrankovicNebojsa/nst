package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.DepartmentDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    DepartmentDto save(DepartmentDto departmentDto) throws Exception;

    List<DepartmentDto> getAll();

    List<DepartmentDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    void update(DepartmentDto department) throws Exception;

    DepartmentDto findById(Long id) throws Exception;
}
