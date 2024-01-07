package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.AcademicTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademicTitleRepository extends JpaRepository<AcademicTitle, Long> {

    Optional<AcademicTitle> findByName(String name);
}
