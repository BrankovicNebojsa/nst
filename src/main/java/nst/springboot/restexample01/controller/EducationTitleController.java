package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.service.EducationTitleService;
import nst.springboot.restexample01.dto.EducationTitleDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education-title")
public class EducationTitleController {

    private EducationTitleService educationTitleService;

    public EducationTitleController(EducationTitleService educationTitleService) {
        this.educationTitleService = educationTitleService;
    }

    @PostMapping
    public ResponseEntity<EducationTitleDto> save(@Valid @RequestBody EducationTitleDto educationTitleDto) throws Exception {
        //ResponseEntity
        EducationTitleDto educationTitleDto1 = educationTitleService.save(educationTitleDto);
        return new ResponseEntity<>(educationTitleDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EducationTitleDto>> getAll() {
        List<EducationTitleDto> educationTitles = educationTitleService.getAll();
        return new ResponseEntity<>(educationTitles, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<List<EducationTitleDto>> getAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {

        Pageable pageable;
        if (sortDirection.equals("asc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        List<EducationTitleDto> educationTitleDtos = educationTitleService.getAll(pageable);
        return new ResponseEntity<>(educationTitleDtos, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    @GetMapping("/{id}")
    public EducationTitleDto findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return educationTitleService.findById(id);
    }

    //azuriraj



    //obrisi
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {

        educationTitleService.delete(id);
        return new ResponseEntity<>("Education title removed!", HttpStatus.OK);

    }
}
