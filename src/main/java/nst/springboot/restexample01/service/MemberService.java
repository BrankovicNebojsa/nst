/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.MemberDto;

import java.util.List;

public interface MemberService {
    MemberDto save(MemberDto memberDto)throws Exception;
    List<MemberDto> getAll();
    void delete(Long id) throws Exception;
    void update(MemberDto memberDto)throws Exception;
    MemberDto findById(Long id)throws Exception;
}
