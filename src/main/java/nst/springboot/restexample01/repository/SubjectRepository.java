package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.ScientificField;
import nst.springboot.restexample01.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);
}
