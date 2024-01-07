/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter implements DtoEntityConverter<MemberDto, Member> {
    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;
    @Autowired
    private EducationTitleConverter educationTitleConverter;


    @Override
    public MemberDto toDto(Member entity) {
        return new MemberDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                departmentConverter.toDto(entity.getDepartment()),
                academicTitleConverter.toDto(entity.getAcademicTitle()),
                scientificFieldConverter.toDto(entity.getScientificField()),
                educationTitleConverter.toDto(entity.getEducationTitle())
        );
    }

    @Override
    public Member toEntity(MemberDto dto) {
        return new Member(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                departmentConverter.toEntity(dto.getDepartmentDto()),
                academicTitleConverter.toEntity(dto.getAcademicTitleDto()),
                scientificFieldConverter.toEntity(dto.getScientificFieldDto()),
                educationTitleConverter.toEntity(dto.getEducationTitleDto())
        );
    }

}
