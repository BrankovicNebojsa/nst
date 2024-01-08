package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.dto.AdministrationHistoryDto;
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

    @GetMapping("/query")
    public AdministrationHistoryDto queryById(@RequestParam("id") Long id) throws Exception {
        return administrationHistoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        administrationHistoryService.delete(id);
        return new ResponseEntity<>("Administration history removed!", HttpStatus.OK);

    }


}
