package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    <S extends Member> S save(S entity);
}
