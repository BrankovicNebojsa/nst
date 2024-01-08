package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, Long> {
}
