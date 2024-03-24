package nst.springboot.restexample01;

import nst.springboot.restexample01.dto.ScientificFieldDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.service.ScientificFieldService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScientificFieldServiceTest extends AbstractTest {

    @Autowired
    private ScientificFieldService scientificFieldService;

    private ScientificFieldDto scientificFieldDto;

    @BeforeEach
    public void setUp() {
        scientificFieldDto = ApplicationTestData.scientificField;
    }

    @AfterEach
    public void tearDown() {
        List<ScientificFieldDto> scientificFields = scientificFieldService.getAll();
        scientificFields.forEach(scientificFieldDto -> {
            try {
                scientificFieldService.delete(scientificFieldDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void save() throws Exception {
        int initialSize = scientificFieldService.getAll().size();
        scientificFieldService.save(scientificFieldDto);
        List<ScientificFieldDto> scientificFieldDtos = scientificFieldService.getAll();

        assertEquals(initialSize + 1, scientificFieldDtos.size());
        assertThrows(EntityAlreadyExistsException.class, () -> scientificFieldService.save(scientificFieldDto));
    }

    @Test
    public void findById() throws Exception {
        scientificFieldService.save(scientificFieldDto);
        List<ScientificFieldDto> scientificFieldDtos = scientificFieldService.getAll();
        ScientificFieldDto scientificFieldDto = scientificFieldDtos.stream().filter(scientificField -> scientificField.equals(this.scientificFieldDto)).findFirst().get();

        ScientificFieldDto scientificFieldDto1 = scientificFieldService.findById(scientificFieldDto.getId());
        assertEquals(this.scientificFieldDto, scientificFieldDto1);
    }

    @Test
    public void update() throws Exception {
        scientificFieldService.save(scientificFieldDto);
        List<ScientificFieldDto> scientificFields = scientificFieldService.getAll();
        ScientificFieldDto scientificFieldDto1 = scientificFields.stream().filter(scientificField -> scientificField.equals(scientificFieldDto)).findFirst().get();

        scientificFieldDto.setName("New Scientific Field");
        scientificFieldService.update(scientificFieldDto1.getId(), scientificFieldDto);

        scientificFields = scientificFieldService.getAll();
        scientificFieldDto1 = scientificFields.stream().filter(academicTitle -> academicTitle.equals(scientificFieldDto)).findFirst().get();

        assertEquals("New Scientific Field", scientificFieldDto1.getName());
    }

    @Test
    public void delete() throws Exception {
        scientificFieldService.save(scientificFieldDto);
        List<ScientificFieldDto> scientificFields = scientificFieldService.getAll();
        ScientificFieldDto scientificFieldDto1 = scientificFields.stream().filter(scientificField -> scientificField.equals(scientificFieldDto)).findFirst().get();

        scientificFieldService.delete(scientificFieldDto1.getId());
        assertThrows(Exception.class, () -> scientificFieldService.findById(scientificFieldDto1.getId()));
    }

}
