package nst.springboot.restexample01;

import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.SubjectDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.service.DepartmentService;
import nst.springboot.restexample01.service.SubjectService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubjectServiceTest extends AbstractTest {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private DepartmentService departmentService;

    private SubjectDto subjectDto;
    private DepartmentDto departmentDto;

    @BeforeEach
    public void setUp() throws Exception {
        departmentDto = departmentService.save(ApplicationTestData.department);
        subjectDto = ApplicationTestData.subject;
    }

    @AfterEach
    public void tearDown() throws Exception {
        List<SubjectDto> subjects = subjectService.getAll();
        subjects.forEach(subject -> {
            try {
                subjectService.delete(subject.getId());
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
    }

    @Test
    public void save() throws Exception {
        int initialSize = subjectService.getAll().size();
        subjectDto.setDepartmentDto(departmentDto);
        subjectService.save(subjectDto);
        List<SubjectDto> subjects = subjectService.getAll();

        assertEquals(initialSize + 1, subjects.size());
        assertThrows(EntityAlreadyExistsException.class, () -> subjectService.save(subjectDto));
    }

    @Test
    public void findById() throws Exception {
        subjectDto.setDepartmentDto(departmentDto);
        subjectService.save(subjectDto);
        List<SubjectDto> subjects = subjectService.getAll();
        SubjectDto subjectDto1 = subjects.stream().filter(subjectDto -> subjectDto.equals(this.subjectDto)).findFirst().get();

        SubjectDto subject = subjectService.findById(subjectDto1.getId());
        assertEquals(subjectDto, subject);
    }

    @Test
    public void update() throws Exception {
        subjectDto.setDepartmentDto(departmentDto);
        subjectService.save(subjectDto);
        List<SubjectDto> subjects = subjectService.getAll();
        SubjectDto subjectDto1 = subjects.stream().filter(subject -> subject.equals(subjectDto)).findFirst().get();

        subjectDto.setName("New Subject");
        subjectService.update(subjectDto1.getId(), subjectDto);

        subjects = subjectService.getAll();
        subjectDto1 = subjects.stream().filter(subject -> subject.equals(subjectDto)).findFirst().get();

        assertEquals("New Subject", subjectDto1.getName());
    }

    @Test
    public void delete() throws Exception {
        subjectDto.setDepartmentDto(departmentDto);
        subjectService.save(subjectDto);
        List<SubjectDto> subjects = subjectService.getAll();
        SubjectDto subjectDto1 = subjects.stream().filter(subject -> subject.equals(subjectDto)).findFirst().get();

        subjectService.delete(subjectDto1.getId());
        assertThrows(Exception.class, () -> subjectService.findById(subjectDto1.getId()));
    }
}
