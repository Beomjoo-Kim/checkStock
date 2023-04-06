package self.prac.checkStock.admin.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.admin.domain.Admin;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    @Test
    @Transactional
    @Rollback
    public void save() {
        //given
        Admin admin = new Admin();
        admin.setEmail("asd");
        admin.setName("adminA");
        admin.setPassword("123");

        //when
        long id = adminRepository.save(admin);
        Admin searchedAdmin = adminRepository.findOne(id);

        //then
        assertThat(admin).isEqualTo(searchedAdmin);
    }

    @Test
    @Transactional
    @Rollback
    public void find() {
        //given
        Admin admin1 = new Admin();
        admin1.setName("testA");
        admin1.setEmail("asd1");
        admin1.setPassword("a1");
        adminRepository.save(admin1);

        Admin admin2 = new Admin();
        admin2.setName("testB");
        admin2.setEmail("asd2");
        admin2.setPassword("a2");
        adminRepository.save(admin2);

        //when
        List<Admin> searchedMemberList1 = adminRepository.findAll();
        List<Admin> searchedMemberList2 = adminRepository.findByEmail("asd1");
        List<Admin> searchedMemberList3 = adminRepository.findByName("test");
        List<Admin> searchedMemberList4 = adminRepository.findByName("testA");

        //then
        assertThat(searchedMemberList1).contains(admin1, admin2);
        assertThat(searchedMemberList2.get(0)).isEqualTo(admin1);
        assertThat(searchedMemberList3).contains(admin1, admin2);
        assertThat(searchedMemberList4.get(0)).isEqualTo(admin1);
    }

}