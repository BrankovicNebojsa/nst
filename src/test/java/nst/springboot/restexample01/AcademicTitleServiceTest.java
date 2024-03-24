package nst.springboot.restexample01;

import nst.springboot.restexample01.dto.AcademicTitleDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.service.AcademicTitleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AcademicTitleServiceTest extends AbstractTest {

    @Autowired
    private AcademicTitleService academicTitleService;

    private AcademicTitleDto academicTitleDto;

    @BeforeEach
    public void setUp() {
        academicTitleDto = ApplicationTestData.academicTitle;
    }

    @AfterEach
    public void tearDown() {
        List<AcademicTitleDto> academicTitles = this.academicTitleService.getAll();
        academicTitles.forEach(academicTitleDto -> {
            try {
                academicTitleService.delete(academicTitleDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void save() throws Exception {
        int initialSize = academicTitleService.getAll().size();
        academicTitleService.save(academicTitleDto);
        List<AcademicTitleDto> academicTitles = academicTitleService.getAll();

        assertEquals(initialSize + 1, academicTitles.size());
        assertThrows(EntityAlreadyExistsException.class, () -> academicTitleService.save(academicTitleDto));
    }

    @Test
    public void findById() throws Exception {
        academicTitleService.save(academicTitleDto);
        List<AcademicTitleDto> academicTitles = academicTitleService.getAll();
        AcademicTitleDto academicTitleDto1 = academicTitles.stream().filter(academicTitle -> academicTitle.equals(academicTitleDto)).findFirst().get();

        AcademicTitleDto academicTitle = academicTitleService.findById(academicTitleDto1.getId());
        assertEquals(academicTitleDto, academicTitle);
    }

    @Test
    public void update() throws Exception {
        academicTitleService.save(academicTitleDto);
        List<AcademicTitleDto> academicTitles = academicTitleService.getAll();
        AcademicTitleDto academicTitleDto1 = academicTitles.stream().filter(academicTitle -> academicTitle.equals(academicTitleDto)).findFirst().get();

        academicTitleDto.setName("New Academic Title");
        academicTitleService.update(academicTitleDto1.getId(), academicTitleDto);

        academicTitles = academicTitleService.getAll();
        academicTitleDto1 = academicTitles.stream().filter(academicTitle -> academicTitle.equals(academicTitleDto)).findFirst().get();

        assertEquals("New Academic Title", academicTitleDto1.getName());
    }

    @Test
    public void delete() throws Exception {
        academicTitleService.save(academicTitleDto);
        List<AcademicTitleDto> academicTitles = academicTitleService.getAll();
        AcademicTitleDto academicTitleDto1 = academicTitles.stream().filter(academicTitle -> academicTitle.equals(academicTitleDto)).findFirst().get();

        academicTitleService.delete(academicTitleDto1.getId());
        assertThrows(Exception.class, () -> academicTitleService.findById(academicTitleDto1.getId()));
    }

}
