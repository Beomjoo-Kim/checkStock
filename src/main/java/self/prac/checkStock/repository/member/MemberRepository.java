package self.prac.checkStock.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.domain.member.Member;

import javax.persistence.EntityManager;
import java.util.List;

import static self.prac.checkStock.domain.member.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    public long save(Member member) {
        //TODO : 이것도 대체 가능할지도? jpaRepository 를 implements?
        em.persist(member);
        return member.getId();
    }

    public Member findOne(long id) {
        return jpaQueryFactory.selectFrom(member).where(member.id.eq(id)).fetchOne();
    }

    public List<Member> findAll() {
        return jpaQueryFactory.selectFrom(member).fetch();
    }

    public List<Member> findByName(String name) {
        return jpaQueryFactory.selectFrom(member).where(member.name.contains(name)).fetch();
    }

    public List<Member> findByEmail(String email) {
        return jpaQueryFactory.selectFrom(member).where(member.email.eq(email)).fetch();
    }
}
