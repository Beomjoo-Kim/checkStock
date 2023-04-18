package self.prac.checkStock.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.member.domain.MemberStatus;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;
import self.prac.checkStock.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member signUp(Member member) {
        if (memberRepository.findByEmail(member.getEmail()).size() != 0) {
            throw new CustomRuntimeException(CustomErrorCodes.ALREADY_SIGNED);
        }
        memberRepository.save(member);
        return member;
    }

    public Member signIn(Member member) {
        return isPasswordCorrect(member);
    }

    public Member getMemberByEmail(String email) {
        List<Member> memberList = memberRepository.findByEmail(email);
        if (memberList.isEmpty()) {
            throw new CustomRuntimeException(CustomErrorCodes.NOT_SIGNED);
        } else if (memberList.size() > 1) {
            throw new CustomRuntimeException(CustomErrorCodes.OVER_SIGNED);
        }
        return memberList.get(0);
    }

    @Transactional
    public Member isPasswordCorrect(Member member) {
        Member searchedMember = getMemberByEmail(member.getEmail());
        if (!searchedMember.getPassword().equals(member.getPassword())) {
            throw new CustomRuntimeException(CustomErrorCodes.INCORRECT_PASSWORD);
        }
        return searchedMember;
    }

    @Transactional
    public void modifyStatus(Member member, MemberStatus status) {
        member.setStatus(status);
    }

}
