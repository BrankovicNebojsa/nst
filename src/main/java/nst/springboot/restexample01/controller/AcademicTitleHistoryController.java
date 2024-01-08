/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import nst.springboot.restexample01.service.AcademicTitleHistoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic-title-history")
public class AcademicTitleHistoryController {
    private final AcademicTitleHistoryService academicTitleHistoryService;

    public AcademicTitleHistoryController(AcademicTitleHistoryService academicTitleHistoryService) {
        this.academicTitleHistoryService = academicTitleHistoryService;
    }

    @PostMapping
    public ResponseEntity<AcademicTitleHistoryDto> save(@Valid @RequestBody AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception {
        AcademicTitleHistoryDto academicTitleHistoryDto1 = academicTitleHistoryService.save(academicTitleHistoryDto);
        return new ResponseEntity<>(academicTitleHistoryDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AcademicTitleHistoryDto>> getAll() {
        List<AcademicTitleHistoryDto> academicTitleHistoryDtos = academicTitleHistoryService.getAll();
        return new ResponseEntity<>(academicTitleHistoryDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public AcademicTitleHistoryDto findById(@PathVariable("id") Long id) throws Exception {
        return academicTitleHistoryService.findById(id);
    }

    @GetMapping("/query")
    public AcademicTitleHistoryDto queryById(@RequestParam("id") Long id) throws Exception {
        return academicTitleHistoryService.findById(id);
    }

    @GetMapping("/paging")
    public ResponseEntity<List<AcademicTitleHistoryDto>> getAllByPage(
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
        List<AcademicTitleHistoryDto> academicTitleHistoryDtos = academicTitleHistoryService.getAll(pageable);
        return new ResponseEntity<>(academicTitleHistoryDtos, HttpStatus.OK);
    }

    //azuriraj


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        academicTitleHistoryService.delete(id);
        return new ResponseEntity<>("Academic Title History removed!", HttpStatus.OK);

    }


}
