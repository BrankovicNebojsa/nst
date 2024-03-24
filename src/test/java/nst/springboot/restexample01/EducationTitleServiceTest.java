package nst.springboot.restexample01;

import nst.springboot.restexample01.dto.EducationTitleDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.service.EducationTitleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EducationTitleServiceTest extends AbstractTest {

    @Autowired
    private EducationTitleService educationTitleService;

    private EducationTitleDto educationTitleDto;

    @BeforeEach
    public void setUp() {
        educationTitleDto = ApplicationTestData.educationTitle;
    }

    @AfterEach
    public void tearDown() {
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
        int initialSize = educationTitleService.getAll().size();
        educationTitleService.save(educationTitleDto);
        List<EducationTitleDto> educationTitleDtos = educationTitleService.getAll();

        assertEquals(initialSize + 1, educationTitleDtos.size());
        assertThrows(EntityAlreadyExistsException.class, () -> educationTitleService.save(educationTitleDto));
    }

    @Test
    public void findById() throws Exception {
        educationTitleService.save(educationTitleDto);
        List<EducationTitleDto> educationTitles = educationTitleService.getAll();
        EducationTitleDto educationTitleDto = educationTitles.stream().filter(educationTitle -> educationTitle.equals(this.educationTitleDto)).findFirst().get();

        EducationTitleDto educationTitleDto1 = educationTitleService.findById(educationTitleDto.getId());
        assertEquals(this.educationTitleDto, educationTitleDto1);
    }

    @Test
    public void update() throws Exception {
        educationTitleService.save(educationTitleDto);
        List<EducationTitleDto> educationTitles = educationTitleService.getAll();
        EducationTitleDto educationTitleDto1 = educationTitles.stream().filter(educationTitle -> educationTitle.equals(educationTitleDto)).findFirst().get();

        educationTitleDto.setName("New Education Title");
        educationTitleService.update(educationTitleDto1.getId(), educationTitleDto);

        educationTitles = educationTitleService.getAll();
        educationTitleDto1 = educationTitles.stream().filter(educationTitle -> educationTitle.equals(educationTitleDto)).findFirst().get();

        assertEquals("New Education Title", educationTitleDto1.getName());
    }

    @Test
    public void delete() throws Exception {
        educationTitleService.save(educationTitleDto);
        List<EducationTitleDto> educationTitles = educationTitleService.getAll();
        EducationTitleDto educationTitleDto1 = educationTitles.stream().filter(educationTitle -> educationTitle.equals(educationTitleDto)).findFirst().get();

        educationTitleService.delete(educationTitleDto1.getId());
        assertThrows(Exception.class, () -> educationTitleService.findById(educationTitleDto1.getId()));
    }
}
