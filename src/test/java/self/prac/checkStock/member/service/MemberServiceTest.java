package self.prac.checkStock.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;
import self.prac.checkStock.member.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @Transactional
    public void signUp() {
        //given
        Member member1 = Member.builder()
                .email("testEmail@test.com")
                .name("testName")
                .password("testPw")
                .build();
        Member member2 = Member.builder()
                .email("testEmail@test.com")
                .name("testName2")
                .password("testPw")
                .build();

        //when
        memberService.signUp(member1);
        Member searchedMemberByEmail = (Member) memberService.loadUserByUsername(member1.getEmail());

        //then
        assertThat(searchedMemberByEmail).isEqualTo(member1);
        try {
            memberService.signUp(member2);
            fail();
        } catch (CustomRuntimeException e) {
            assertThat(e.getCode()).isEqualTo(CustomErrorCodes.ALREADY_SIGNED);
        }

    }

    @Test
    @Transactional
    public void signIn() {
        //given
        Member member1 = Member.builder()
                .email("testEmail@test.com")
                .name("testName")
                .password("testPw")
                .build();
        memberService.signUp(member1);

        //when
        Member incorrectMember = Member.builder()
                .email("testEmail@test.com")
                .name("testName")
                .password("incorrectTestPw")
                .build();
        Member member2 = Member.builder()
                .email("testEmail@test.com")
                .name("testName")
                .password("testPw")
                .build();

        //then
        try {
            memberService.signIn(incorrectMember);
            fail();
        } catch (CustomRuntimeException e) {
            assertThat(e.getCode()).isEqualTo(CustomErrorCodes.INCORRECT_PASSWORD);
        }
        memberService.signIn(member2);

    }

}
