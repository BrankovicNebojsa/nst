package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.exception.MyErrorDetails;
import nst.springboot.restexample01.service.DepartmentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
        System.out.println("nst.springboot.restexample01.controller.DepartmentController.<init>()");
        System.out.println("kreiran je konroller!");
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> save(@Valid @RequestBody DepartmentDto departmentDto) throws Exception {
        DepartmentDto deptDto = departmentService.save(departmentDto);
        return new ResponseEntity<>(deptDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> departments = departmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<List<DepartmentDto>> getAllByPage(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "pageSize", defaultValue = "2") int pageSize, @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {

        Pageable pageable;
        if (sortDirection.equals("asc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        List<DepartmentDto> departments = departmentService.getAll(pageable);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public DepartmentDto findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return departmentService.findById(id);
    }

    @GetMapping("/query")
    public DepartmentDto queryById(@RequestParam("id") Long id) throws Exception {
        return departmentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        departmentService.delete(id);
        return new ResponseEntity<>("Department removed!", HttpStatus.OK);

    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<MyErrorDetails> handleException(EntityAlreadyExistsException e) {
        System.out.println("nst.springboot.restexample01.controller.DepartmentController.handleException()");
        System.out.println("-----------pozvana metoda za obradu izuzetka u kontroleru -------------");

        MyErrorDetails myErrorDetails = new MyErrorDetails(e.getMessage());

        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);

    }
}
