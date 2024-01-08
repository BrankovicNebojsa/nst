package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.AdministrationHistory;
import nst.springboot.restexample01.dto.AdministrationHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdministrationHistoryConverter implements DtoEntityConverter<AdministrationHistoryDto, AdministrationHistory> {
    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private MemberConverter memberConverter;

    @Override
    public AdministrationHistoryDto toDto(AdministrationHistory entity) {
        return new AdministrationHistoryDto(
                entity.getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                departmentConverter.toDto(entity.getDepartment()),
                memberConverter.toDto(entity.getHeadOfDepartment()),
                memberConverter.toDto(entity.getSecretary())
        );
    }

    @Override
    public AdministrationHistory toEntity(AdministrationHistoryDto dto) {
        return new AdministrationHistory(
                dto.getId(),
                dto.getStartDate(),
                dto.getEndDate(),
                departmentConverter.toEntity(dto.getDepartmentDto()),
                memberConverter.toEntity(dto.getHeadOfDepartmentDto()),
                memberConverter.toEntity(dto.getSecretaryDto())
        );
    }

}
