package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDto> save(@Valid @RequestBody MemberDto memberDto) throws Exception {
        MemberDto memberDto1 = memberService.save(memberDto);
        return new ResponseEntity<>(memberDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAll() {
        List<MemberDto> memberDtos = memberService.getAll();
        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public MemberDto findById(@PathVariable("id") Long id) throws Exception {
        return memberService.findById(id);
    }

    @GetMapping("/department/{id}")
    public List<MemberDto> findDepartmentMembers(@PathVariable("id") Long id) throws Exception {
        return memberService.getAllByDepartmentId(id);
    }

    @GetMapping("/query")
    public MemberDto queryById(@RequestParam("id") Long id) throws Exception {
        return memberService.findById(id);
    }

    @GetMapping("/academic-title-history/{id}")
    public List<AcademicTitleHistoryDto> getAcademicTitleHistoryOfAMember(@RequestParam("id") Long id) throws Exception {
        return memberService.getAcademicTitleHistoryOfMember(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> update(@PathVariable Long id, @RequestBody MemberDto memberDto) throws Exception {
        return new ResponseEntity<>(memberService.update(id, memberDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        memberService.delete(id);
        return new ResponseEntity<>("Member removed!", HttpStatus.OK);

    }


}
