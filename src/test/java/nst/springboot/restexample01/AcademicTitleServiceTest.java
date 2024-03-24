package nst.springboot.restexample01;

import nst.springboot.restexample01.dto.AcademicTitleDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.service.AcademicTitleService;
import nst.springboot.restexample01.service.impl.AcademicTitleServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AcademicTitleServiceTest {


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
                this.academicTitleService.delete(academicTitleDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void save() throws Exception {
        int initialSize = this.academicTitleService.getAll().size();
        this.academicTitleService.save(academicTitleDto);
        List<AcademicTitleDto> academicTitles = this.academicTitleService.getAll();

        assertEquals(initialSize + 1, academicTitles.size());
        assertThrows(EntityAlreadyExistsException.class, () -> this.academicTitleService.save(academicTitleDto));
    }

    @Test
    public void findById() throws Exception {
        this.academicTitleService.save(academicTitleDto);
        List<AcademicTitleDto> academicTitles = this.academicTitleService.getAll();
        AcademicTitleDto academicTitleDto1 = academicTitles.stream().filter(academicTitle -> academicTitle.equals(academicTitleDto)).findFirst().get();

        AcademicTitleDto academicTitle = this.academicTitleService.findById(academicTitleDto1.getId());
        assertEquals(academicTitleDto, academicTitle);
    }

    @Test
    public void update() throws Exception {
        this.academicTitleService.save(academicTitleDto);
        List<AcademicTitleDto> academicTitles = this.academicTitleService.getAll();
        AcademicTitleDto academicTitleDto1 = academicTitles.stream().filter(academicTitle -> academicTitle.equals(academicTitleDto)).findFirst().get();

        academicTitleDto.setName("New Academic Title");
        this.academicTitleService.update(academicTitleDto1.getId(), academicTitleDto);

        academicTitles = this.academicTitleService.getAll();
        academicTitleDto1 = academicTitles.stream().filter(academicTitle -> academicTitle.equals(academicTitleDto)).findFirst().get();

        assertEquals("New Academic Title", academicTitleDto1.getName());
    }

    @Test
    public void delete() throws Exception {
        this.academicTitleService.save(academicTitleDto);
        List<AcademicTitleDto> academicTitles = this.academicTitleService.getAll();
        AcademicTitleDto academicTitleDto1 = academicTitles.stream().filter(academicTitle -> academicTitle.equals(academicTitleDto)).findFirst().get();

        this.academicTitleService.delete(academicTitleDto1.getId());
        assertThrows(Exception.class, () -> this.academicTitleService.findById(academicTitleDto1.getId()));
    }


}
