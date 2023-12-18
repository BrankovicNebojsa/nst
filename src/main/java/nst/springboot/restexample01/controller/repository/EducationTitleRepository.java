package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.EducationTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationTitleRepository extends JpaRepository<EducationTitle, Long> {

    Optional<EducationTitle> findByName(String name);
}
