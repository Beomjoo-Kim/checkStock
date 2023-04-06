package self.prac.checkStock.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.TestConfig;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;
import self.prac.checkStock.member.repository.MemberRepository;
import self.prac.checkStock.member.service.MemberService;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Import(TestConfig.class)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @Transactional
    public void signUp() {
        //given
        Member member = new Member("testEmail@test.com", "testName", "testPw", "123");
        Member member2 = new Member("testEmail@test.com", "testName2", "testPw2", "123");

        //when
        memberService.signUp(member);
        Member searchedMemberByEmail = memberService.getMemberByEmail(member.getEmail());

        //then
        assertThat(searchedMemberByEmail).isEqualTo(member);
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
        Member member = new Member("testEmail@test.com", "testName", "testPw", "123");
        memberService.signUp(member);

        //when
        Member incorrectMember = new Member("testEmail@test.com", "testName", "incorrectTestPw", "123");
        Member member2 = new Member("testEmail@test.com", "testName", "testPw", "123");

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
