package nst.springboot.restexample01;

import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.service.DepartmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepartmentServiceTest extends AbstractTest {
    @Autowired
    private DepartmentService departmentService;
    private DepartmentDto departmentDto;

    @BeforeEach
    public void setUp() throws Exception {
        departmentDto = ApplicationTestData.department;
    }

    @AfterEach
    public void tearDown() throws Exception {
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
        int initialSize = departmentService.getAll().size();
        departmentService.save(departmentDto);
        List<DepartmentDto> departments = departmentService.getAll();

        assertEquals(initialSize + 1, departments.size());
        assertThrows(EntityAlreadyExistsException.class, () -> departmentService.save(departmentDto));
    }

    @Test
    public void findById() throws Exception {
        departmentService.save(departmentDto);
        List<DepartmentDto> departments = departmentService.getAll();
        DepartmentDto departmentDto1 = departments.stream().filter(departmentDto -> departmentDto.equals(this.departmentDto)).findFirst().get();

        DepartmentDto department = departmentService.findById(departmentDto1.getId());
        assertEquals(departmentDto, department);
    }

    @Test
    public void update() throws Exception {
        departmentService.save(departmentDto);
        List<DepartmentDto> departments = departmentService.getAll();
        DepartmentDto departmentDto1 = departments.stream().filter(department -> department.equals(departmentDto)).findFirst().get();

        departmentDto.setName("New Department");
        departmentService.update(departmentDto1.getId(), departmentDto);

        departments = departmentService.getAll();
        departmentDto1 = departments.stream().filter(subject -> subject.equals(departmentDto)).findFirst().get();

        assertEquals("New Department", departmentDto1.getName());
    }

    @Test
    public void delete() throws Exception {
        departmentService.save(departmentDto);
        List<DepartmentDto> departments = departmentService.getAll();
        DepartmentDto departmentDto1 = departments.stream().filter(department -> department.equals(departmentDto)).findFirst().get();

        departmentService.delete(departmentDto1.getId());
        assertThrows(Exception.class, () -> departmentService.findById(departmentDto1.getId()));
    }
}
