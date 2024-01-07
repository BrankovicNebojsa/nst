package nst.springboot.restexample01.converter.impl;

import nst.springboot.restexample01.converter.DtoEntityConverter;
import nst.springboot.restexample01.domain.AcademicTitle;
import nst.springboot.restexample01.domain.ScientificField;
import nst.springboot.restexample01.dto.AcademicTitleDto;
import nst.springboot.restexample01.dto.ScientificFieldDto;
import org.springframework.stereotype.Component;

@Component
public class ScientificFieldConverter implements DtoEntityConverter<ScientificFieldDto, ScientificField> {
    @Override
    public ScientificFieldDto toDto(ScientificField scientificField) {
        return new ScientificFieldDto(scientificField.getId(), scientificField.getName());
    }

    @Override
    public ScientificField toEntity(ScientificFieldDto scientificFieldDto) {
        return new ScientificField(scientificFieldDto.getId(), scientificFieldDto.getName());
    }
}
