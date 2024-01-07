/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.service;

import java.util.List;
import nst.springboot.restexample01.dto.DepartmentDto;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    DepartmentDto save(DepartmentDto departmentDto) throws Exception;
    List<DepartmentDto> getAll(Pageable pageable);
    List<DepartmentDto> getAll();
    void delete(Long id) throws Exception;
    void update(DepartmentDto department) throws Exception;
    DepartmentDto findById(Long id) throws Exception;
}
