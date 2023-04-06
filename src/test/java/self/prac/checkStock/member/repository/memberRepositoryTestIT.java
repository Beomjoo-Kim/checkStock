package self.prac.checkStock.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.member.domain.MemberStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class memberRepositoryTestIT {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void save() {
        //given
        Member member = new Member("asd", "testA", "1234", "1234");
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
        Member member1 = new Member("asd1", "testA", "a1", "123");
        memberRepository.save(member1);

        Member member2 = new Member("asd2", "testB", "a2", "123");
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