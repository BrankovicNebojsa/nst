package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.EducationTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EducationTitleRepository extends JpaRepository<EducationTitle, Long> {

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public <S extends EducationTitle> S save(S entity);

    Optional<EducationTitle> findByName(String name);
}