/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.AcademicTitleHistory;
import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcademicTitleHistoryConverter implements DtoEntityConverter<AcademicTitleHistoryDto, AcademicTitleHistory> {
    @Autowired
    private MemberConverter memberConverter;
    @Autowired
    private AcademicTitleConverter academicTitleConverter;
    @Autowired
    private ScientificFieldConverter scientificFieldConverter;

    @Override
    public AcademicTitleHistoryDto toDto(AcademicTitleHistory entity) {
        return new AcademicTitleHistoryDto(
                entity.getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                memberConverter.toDto(entity.getMember()),
                academicTitleConverter.toDto(entity.getAcademicTitle()),
                scientificFieldConverter.toDto(entity.getScientificField())
        );
    }

    @Override
    public AcademicTitleHistory toEntity(AcademicTitleHistoryDto dto) {
        return new AcademicTitleHistory(
                dto.getId(),
                dto.getStartDate(),
                dto.getEndDate(),
                memberConverter.toEntity(dto.getMemberDto()),
                academicTitleConverter.toEntity(dto.getAcademicTitleDto()),
                scientificFieldConverter.toEntity(dto.getScientificFieldDto())
        );
    }

}
