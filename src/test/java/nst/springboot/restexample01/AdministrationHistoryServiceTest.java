package nst.springboot.restexample01;

import nst.springboot.restexample01.dto.*;
import nst.springboot.restexample01.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdministrationHistoryServiceTest extends AbstractTest {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AcademicTitleService academicTitleService;
    @Autowired
    private ScientificFieldService scientificFieldService;
    @Autowired
    private EducationTitleService educationTitleService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private AdministrationHistoryService administrationHistoryService;

    private DepartmentDto departmentDto;
    private AcademicTitleDto academicTitleDto;
    private ScientificFieldDto scientificFieldDto;
    private EducationTitleDto educationTitleDto;
    private MemberDto memberDto;
    private AdministrationHistoryDto administrationHistoryDto;

    @BeforeEach
    public void setUp() throws Exception {
        departmentDto = departmentService.save(ApplicationTestData.department);
        academicTitleDto = academicTitleService.save(ApplicationTestData.academicTitle);
        scientificFieldDto = scientificFieldService.save(ApplicationTestData.scientificField);
        educationTitleDto = educationTitleService.save(ApplicationTestData.educationTitle);
        memberDto = ApplicationTestData.member;
        memberDto.setDepartmentDto(departmentDto);
        memberDto.setAcademicTitleDto(academicTitleDto);
        memberDto.setScientificFieldDto(scientificFieldDto);
        memberDto.setEducationTitleDto(educationTitleDto);
        memberDto = memberService.save(memberDto);
        administrationHistoryDto = ApplicationTestData.administrationHistory;
        administrationHistoryDto.setDepartmentDto(departmentDto);
        administrationHistoryDto.setSecretaryDto(memberDto);
        administrationHistoryDto.setHeadOfDepartmentDto(memberDto);
    }

    @AfterEach
    public void tearDown() {
        List<AdministrationHistoryDto> academicTitleHistoryDtoList = administrationHistoryService.getAll();
        academicTitleHistoryDtoList.forEach(academicTitleHistoryDto -> {
            try {
                administrationHistoryService.delete(academicTitleHistoryDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<MemberDto> members = memberService.getAll();
        members.forEach(member -> {
            try {
                memberService.delete(member.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<DepartmentDto> departments = departmentService.getAll();
        departments.forEach(department -> {
            try {
                departmentService.delete(department.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<AcademicTitleDto> academicTitles = academicTitleService.getAll();
        academicTitles.forEach(academicTitleDto -> {
            try {
                academicTitleService.delete(academicTitleDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<ScientificFieldDto> scientificFields = scientificFieldService.getAll();
        scientificFields.forEach(scientificFieldDto -> {
            try {
                scientificFieldService.delete(scientificFieldDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<EducationTitleDto> educationTitles = educationTitleService.getAll();
        educationTitles.forEach(educationTitleDto -> {
            try {
                educationTitleService.delete(educationTitleDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void save() throws Exception {
        int initialSize = administrationHistoryService.getAll().size();
        administrationHistoryService.save(administrationHistoryDto);
        List<AdministrationHistoryDto> administrationHistoryDtos = administrationHistoryService.getAll();

        assertEquals(initialSize + 1, administrationHistoryDtos.size());
    }

    @Test
    public void findById() throws Exception {
        administrationHistoryService.save(administrationHistoryDto);
        List<AdministrationHistoryDto> administrationHistoryDtos = administrationHistoryService.getAll();
        AdministrationHistoryDto administrationHistoryDto1 = administrationHistoryDtos.stream().filter(administrationHistoryDto -> administrationHistoryDto.equals(this.administrationHistoryDto)).findFirst().get();

        AdministrationHistoryDto administrationHistoryDto2 = administrationHistoryService.findById(administrationHistoryDto1.getId());
        assertEquals(administrationHistoryDto, administrationHistoryDto2);
    }

    @Test
    public void update() throws Exception {
        administrationHistoryService.save(administrationHistoryDto);
        List<AdministrationHistoryDto> administrationHistoryDtos = administrationHistoryService.getAll();
        AdministrationHistoryDto administrationHistoryDto1 = administrationHistoryDtos.stream().filter(administrationHistoryDto -> administrationHistoryDto.equals(this.administrationHistoryDto)).findFirst().get();

        Date startDate = ApplicationTestData.parseDate("2000-01-01", "yyyy-MM-dd");
        Date endDate = ApplicationTestData.parseDate("2010-01-01", "yyyy-MM-dd");

        administrationHistoryDto.setStartDate(startDate);
        administrationHistoryDto.setEndDate(endDate);
        administrationHistoryService.update(administrationHistoryDto1.getId(), administrationHistoryDto);

        administrationHistoryDtos = administrationHistoryService.getAll();
        administrationHistoryDto1 = administrationHistoryDtos.stream().filter(administrationHistoryDto -> administrationHistoryDto.equals(this.administrationHistoryDto)).findFirst().get();

        assertEquals(0, startDate.compareTo(administrationHistoryDto1.getStartDate()));
        assertEquals(0, endDate.compareTo(administrationHistoryDto1.getEndDate()));
    }

    @Test
    public void delete() throws Exception {
        administrationHistoryService.save(administrationHistoryDto);
        List<AdministrationHistoryDto> administrationHistoryDtos = administrationHistoryService.getAll();
        AdministrationHistoryDto administrationHistoryDto1 = administrationHistoryDtos.stream().filter(administrationHistoryDto -> administrationHistoryDto.equals(this.administrationHistoryDto)).findFirst().get();

        administrationHistoryService.delete(administrationHistoryDto1.getId());
        assertThrows(Exception.class, () -> administrationHistoryService.findById(administrationHistoryDto1.getId()));
    }
}
