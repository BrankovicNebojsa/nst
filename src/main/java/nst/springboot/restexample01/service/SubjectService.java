/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.service;

import java.util.List;

import nst.springboot.restexample01.dto.SubjectDto;
import org.springframework.data.domain.Pageable;

public interface SubjectService {
    SubjectDto save(SubjectDto subjectDto) throws Exception;

    List<SubjectDto> getAll();

    List<SubjectDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    void update(SubjectDto subjectDto) throws Exception;

    SubjectDto findById(Long id) throws Exception;
}
