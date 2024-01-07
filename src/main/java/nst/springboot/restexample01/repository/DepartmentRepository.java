/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.repository;

import java.util.Optional;
import nst.springboot.restexample01.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author student2
 */
//@Transactional(propagation = Propagation.MANDATORY)
public interface DepartmentRepository extends JpaRepository<Department, Long>{

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public <S extends Department> S save(S entity);
  
    //vrati depratment na osnovu imena
    Optional<Department> findByName(String name);
}
