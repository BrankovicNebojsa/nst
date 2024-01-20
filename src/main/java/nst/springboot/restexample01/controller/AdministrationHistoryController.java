package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.dto.AdministrationHistoryDto;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.service.AdministrationHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administration-history")
public class AdministrationHistoryController {
    private final AdministrationHistoryService administrationHistoryService;

    public AdministrationHistoryController(AdministrationHistoryService administrationHistoryService) {
        this.administrationHistoryService = administrationHistoryService;
    }

    @PostMapping
    public ResponseEntity<AdministrationHistoryDto> save(@Valid @RequestBody AdministrationHistoryDto administrationHistoryDto) throws Exception {
        AdministrationHistoryDto administrationHistoryDto1 = administrationHistoryService.save(administrationHistoryDto);
        return new ResponseEntity<>(administrationHistoryDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdministrationHistoryDto>> getAll() {
        List<AdministrationHistoryDto> administrationHistoryDtos = administrationHistoryService.getAll();
        return new ResponseEntity<>(administrationHistoryDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public AdministrationHistoryDto findById(@PathVariable("id") Long id) throws Exception {
        return administrationHistoryService.findById(id);
    }

    @GetMapping("/head-of-department/{id}")
    public MemberDto findCurrentHeadOfDepartmentByDepartmentId(@PathVariable("id") Long id) throws Exception {
        return administrationHistoryService.findCurrentHeadOfDepartmentByDepartmentId(id);
    }

    @GetMapping("/secretary-of-department/{id}")
    public MemberDto findCurrentSecretaryByDepartmentId(@PathVariable("id") Long id) throws Exception {
        return administrationHistoryService.findCurrentSecretaryByDepartmentId(id);
    }

    @GetMapping("/query")
    public AdministrationHistoryDto queryById(@RequestParam("id") Long id) throws Exception {
        return administrationHistoryService.findById(id);
    }

    @GetMapping("/department/{id}")
    public List<AdministrationHistoryDto> getAdministrationHistoryByDepartment(@RequestParam("id") Long id) throws Exception {
        return administrationHistoryService.getByDepartmentId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministrationHistoryDto> update(@PathVariable Long id, @RequestBody AdministrationHistoryDto administrationHistoryDto) throws Exception {
        return new ResponseEntity<>(administrationHistoryService.update(id, administrationHistoryDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        administrationHistoryService.delete(id);
        return new ResponseEntity<>("Administration history removed!", HttpStatus.OK);

    }


}
