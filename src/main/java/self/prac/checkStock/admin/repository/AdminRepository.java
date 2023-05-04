package self.prac.checkStock.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.prac.checkStock.admin.domain.Admin;

import java.util.List;



@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findByNameContains(String name);
    List<Admin> findByEmail(String email);
}
