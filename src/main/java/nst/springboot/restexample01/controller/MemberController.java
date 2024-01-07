/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.dto.SubjectDto;
import nst.springboot.restexample01.service.MemberService;
import nst.springboot.restexample01.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
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

    @GetMapping("/query")
    public MemberDto queryById(@RequestParam("id") Long id) throws Exception {
        return memberService.findById(id);
    }

    //azuriraj



    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        memberService.delete(id);
        return new ResponseEntity<>("Member removed!", HttpStatus.OK);

    }


}
