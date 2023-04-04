package self.prac.checkStock.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.prac.checkStock.domain.member.Member;
import self.prac.checkStock.exception.CustomErrorCodes;
import self.prac.checkStock.exception.CustomRuntimeException;
import self.prac.checkStock.repository.member.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member signUp(Member member) {
        if (memberRepository.findByEmail(member.getEmail()).size() != 0) {
            throw new CustomRuntimeException(CustomErrorCodes.ALREADY_SIGNED);
        }
        memberRepository.save(member);
        return member;
    }

    public void signIn(Member member) {
        if (!isPasswordCorrect(member)) {
            throw new CustomRuntimeException(CustomErrorCodes.INCORRECT_PASSWORD);
        }
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

    public boolean isPasswordCorrect(Member member) {
        Member searchedMember = getMemberByEmail(member.getEmail());
        return searchedMember.getPassword().equals(member.getPassword());
    }

}
