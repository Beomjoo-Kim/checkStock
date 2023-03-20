package self.prac.checkStock.repository.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.TestConfig;
import self.prac.checkStock.domain.member.Member;
import self.prac.checkStock.domain.member.MemberStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//test 에서 jpa 를 사용하기 위해 testConfig 에서 따로 bean 을 등록.
@Import({TestConfig.class})
public class memberRepositoryTestIT {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void save() {
        //given
        Member member = new Member();
        member.setName("testA");
        member.setPhone("1234");
        member.setEmail("asd");
        member.setStatus(MemberStatus.WITHDRAW);

        //when
        memberRepository.save(member);
        long id = member.getId();
        Member searchedMember = memberRepository.findOne(id);

        //then
        assertThat(member).isEqualTo(searchedMember);
        System.out.println(searchedMember);
    }

    @Test
    @Transactional
    @Rollback
    public void find() {
        //given
        Member member1 = new Member();
        member1.setName("testA");
        member1.setPhone("123");
        member1.setEmail("asd1");
        member1.setPassword("a1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("testB");
        member2.setPhone("123");
        member2.setEmail("asd2");
        member2.setPassword("a2");
        memberRepository.save(member2);

        //when
        List<Member> searchedMemberList1 = memberRepository.findAll();
        List<Member> searchedMemberList2 = memberRepository.findByEmail("asd1");
        List<Member> searchedMemberList3 = memberRepository.findByName("test");
        List<Member> searchedMemberList4 = memberRepository.findByName("testA");

        //then
        assertThat(searchedMemberList1).contains(member1, member2);
        assertThat(searchedMemberList2.get(0)).isEqualTo(member1);
        assertThat(searchedMemberList3).contains(member1, member2);
        assertThat(searchedMemberList4.get(0)).isEqualTo(member1);
    }


}