package self.prac.checkStock.repository.admin;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.domain.admin.Admin;

import javax.persistence.EntityManager;
import java.util.List;

import static self.prac.checkStock.domain.admin.QAdmin.admin;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public long save(Admin admin) {
        em.persist(admin);
        return admin.getId();
    }

    public Admin findOne(long id) {
        return jpaQueryFactory.selectFrom(admin).where(admin.id.eq(id)).fetchOne();
    }

    public List<Admin> findAll() {
        return jpaQueryFactory.selectFrom(admin).fetch();
    }

    public List<Admin> findByName(String name) {
        return jpaQueryFactory.selectFrom(admin).where(admin.name.contains(name)).fetch();
    }

    public List<Admin> findByEmail(String email) {
        return jpaQueryFactory.selectFrom(admin).where(admin.email.eq(email)).fetch();
    }
}
