package self.prac.checkStock.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.member.domain.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByEmail(String email);

    List<Member> findByNameContains(String name);

}
