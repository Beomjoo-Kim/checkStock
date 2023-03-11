package self.prac.checkStock.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.domain.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class memberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback
    public void memberRepositoryIt() throws Exception {
        //given
        Member member = new Member();
        member.setName("testA");
        member.setPhone("1234");
        member.setEmail("asd");

        //when
        memberRepository.save(member);
        long id = member.getId();
        Member searchedMember = memberRepository.findOne(id);

        //then
        assertThat(member).isEqualTo(searchedMember);
    }
}