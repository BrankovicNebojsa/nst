package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
