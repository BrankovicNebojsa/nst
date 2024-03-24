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

public class AcademicTitleHistoryServiceTest extends AbstractTest {
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
    private AcademicTitleHistoryService academicTitleHistoryService;

    private DepartmentDto departmentDto;
    private AcademicTitleDto academicTitleDto;
    private ScientificFieldDto scientificFieldDto;
    private EducationTitleDto educationTitleDto;
    private MemberDto memberDto;
    private AcademicTitleHistoryDto academicTitleHistoryDto;

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
        academicTitleHistoryDto = ApplicationTestData.academicTitleHistory;
        academicTitleHistoryDto.setMemberDto(memberDto);
        academicTitleHistoryDto.setAcademicTitleDto(academicTitleDto);
        academicTitleHistoryDto.setScientificFieldDto(scientificFieldDto);
    }

    @AfterEach
    public void tearDown() {
        List<AcademicTitleHistoryDto> academicTitleHistoryDtos = academicTitleHistoryService.getAll();
        academicTitleHistoryDtos.forEach(academicTitleHistoryDto -> {
            try {
                academicTitleHistoryService.delete(academicTitleHistoryDto.getId());
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
        int initialSize = academicTitleHistoryService.getAll().size();
        academicTitleHistoryService.save(academicTitleHistoryDto);
        List<AcademicTitleHistoryDto> academicTitleHistoryDtos = academicTitleHistoryService.getAll();

        assertEquals(initialSize + 1, academicTitleHistoryDtos.size());
    }

    @Test
    public void findById() throws Exception {
        academicTitleHistoryService.save(academicTitleHistoryDto);
        List<AcademicTitleHistoryDto> academicTitleHistoryDtos = academicTitleHistoryService.getAll();
        AcademicTitleHistoryDto academicTitleHistoryDto1 = academicTitleHistoryDtos.stream().filter(academicTitleHistoryDto -> academicTitleHistoryDto.equals(this.academicTitleHistoryDto)).findFirst().get();

        AcademicTitleHistoryDto academicTitleHistoryDto2 = academicTitleHistoryService.findById(academicTitleHistoryDto1.getId());
        assertEquals(academicTitleHistoryDto, academicTitleHistoryDto2);
    }

    @Test
    public void update() throws Exception {
        academicTitleHistoryService.save(academicTitleHistoryDto);
        List<AcademicTitleHistoryDto> academicTitleHistoryDtos = academicTitleHistoryService.getAll();
        AcademicTitleHistoryDto academicTitleHistoryDto1 = academicTitleHistoryDtos.stream().filter(academicTitleHistoryDto -> academicTitleHistoryDto.equals(this.academicTitleHistoryDto)).findFirst().get();

        Date startDate = ApplicationTestData.parseDate("2000-01-01", "yyyy-MM-dd");
        Date endDate = ApplicationTestData.parseDate("2010-01-01", "yyyy-MM-dd");

        academicTitleHistoryDto.setStartDate(startDate);
        academicTitleHistoryDto.setEndDate(endDate);
        academicTitleHistoryService.update(academicTitleHistoryDto1.getId(), academicTitleHistoryDto);

        academicTitleHistoryDtos = academicTitleHistoryService.getAll();
        academicTitleHistoryDto1 = academicTitleHistoryDtos.stream().filter(academicTitleHistoryDto -> academicTitleHistoryDto.equals(this.academicTitleHistoryDto)).findFirst().get();

        assertEquals(0, startDate.compareTo(academicTitleHistoryDto1.getStartDate()));
        assertEquals(0, endDate.compareTo(academicTitleHistoryDto1.getEndDate()));
    }

    @Test
    public void delete() throws Exception {
        academicTitleHistoryService.save(academicTitleHistoryDto);
        List<AcademicTitleHistoryDto> academicTitleHistoryDtos = academicTitleHistoryService.getAll();
        AcademicTitleHistoryDto academicTitleHistoryDto1 = academicTitleHistoryDtos.stream().filter(academicTitleHistoryDto -> academicTitleHistoryDto.equals(this.academicTitleHistoryDto)).findFirst().get();

        academicTitleHistoryService.delete(academicTitleHistoryDto1.getId());
        assertThrows(Exception.class, () -> academicTitleHistoryService.findById(academicTitleHistoryDto1.getId()));
    }
}
