package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, Long> {
    Optional<List<AcademicTitleHistory>> findByMemberId(Long id);

}
