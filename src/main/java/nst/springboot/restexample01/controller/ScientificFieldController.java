package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.dto.ScientificFieldDto;
import nst.springboot.restexample01.service.ScientificFieldService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scientific-field")
public class ScientificFieldController {
    private ScientificFieldService scientificFieldService;

    public ScientificFieldController(ScientificFieldService scientificFieldService) {
        this.scientificFieldService = scientificFieldService;
    }

    @PostMapping
    public ResponseEntity<ScientificFieldDto> save(@Valid @RequestBody ScientificFieldDto scientificFieldDto) throws Exception {
        ScientificFieldDto scientificFieldDto1 = scientificFieldService.save(scientificFieldDto);
        return new ResponseEntity<>(scientificFieldDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScientificFieldDto>> getAll() {
        List<ScientificFieldDto> scientificFieldDtos = scientificFieldService.getAll();
        return new ResponseEntity<>(scientificFieldDtos, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<List<ScientificFieldDto>> getAllByPage(
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
        List<ScientificFieldDto> scientificFieldDtos = scientificFieldService.getAll(pageable);
        return new ResponseEntity<>(scientificFieldDtos, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    @GetMapping("/{id}")
    public ScientificFieldDto findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return scientificFieldService.findById(id);
    }

    //azuriraj


    //obrisi
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        scientificFieldService.delete(id);
        return new ResponseEntity<>("Scientific field removed!", HttpStatus.OK);

    }
}
