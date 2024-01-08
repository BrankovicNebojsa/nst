package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.dto.AcademicTitleDto;
import nst.springboot.restexample01.service.AcademicTitleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic-titles")
public class AcademicTitleController {
    private AcademicTitleService academicTitleService;

    public AcademicTitleController(AcademicTitleService academicTitleService) {
        this.academicTitleService = academicTitleService;
    }

    @PostMapping
    public ResponseEntity<AcademicTitleDto> save(@Valid @RequestBody AcademicTitleDto academicTitleDto) throws Exception {
        AcademicTitleDto academicTitleDto1 = academicTitleService.save(academicTitleDto);
        return new ResponseEntity<>(academicTitleDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AcademicTitleDto>> getAll() {
        List<AcademicTitleDto> academicTitles = academicTitleService.getAll();
        return new ResponseEntity<>(academicTitles, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<List<AcademicTitleDto>> getAllByPage(
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
        List<AcademicTitleDto> academicTitleDtos = academicTitleService.getAll(pageable);
        return new ResponseEntity<>(academicTitleDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public AcademicTitleDto findById(@PathVariable("id") Long id) throws Exception {
        return academicTitleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        academicTitleService.delete(id);
        return new ResponseEntity<>("Academic title removed!", HttpStatus.OK);

    }

}
