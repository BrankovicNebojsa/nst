package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.AdministrationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AdministrationHistoryRepository extends JpaRepository<AdministrationHistory, Long> {

    Optional<AdministrationHistory> findByEndDateAndDepartmentId(Date date, Long id);

}
