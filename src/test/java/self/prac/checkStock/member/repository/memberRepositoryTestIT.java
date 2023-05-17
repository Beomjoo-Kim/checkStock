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
        Member member = Member.builder()
                .email("asd")
                .name("testA")
                .password("1234")
                .build();
        member.setStatus(MemberStatus.WITHDRAW);


        //when
        memberRepository.save(member);
        Long id = member.getId();
        Member searchedMember = memberRepository.findById(id).get();

        //then
        assertThat(member).isEqualTo(searchedMember);
        System.out.println(searchedMember);
    }

    @Test
    @Transactional
    @Rollback
    public void find() {
        //given
        Member member1 = Member.builder()
                .email("asd1")
                .password("testA")
                .name("a1")
                .build();

        memberRepository.save(member1);

        Member member2 = Member.builder()
                .email("asd2")
                .password("testB")
                .name("a2")
                .build();
        memberRepository.save(member2);

        //when
        List<Member> searchedMemberList1 = memberRepository.findAll();
        List<Member> searchedMemberList2 = memberRepository.findByEmail("asd1");
        List<Member> searchedMemberList3 = memberRepository.findByNameContains("test");
        List<Member> searchedMemberList4 = memberRepository.findByNameContains("testA");

        //then
        assertThat(searchedMemberList1).contains(member1, member2);
        assertThat(searchedMemberList2.get(0)).isEqualTo(member1);
        assertThat(searchedMemberList3).contains(member1, member2);
        assertThat(searchedMemberList4.get(0)).isEqualTo(member1);
    }


}