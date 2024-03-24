package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.ScientificField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ScientificFieldRepository extends JpaRepository<ScientificField, Long> {
    Optional<ScientificField> findByName(String name);
}
