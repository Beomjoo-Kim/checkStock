package self.prac.checkStock.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.prac.checkStock.domain.member.Member;
import self.prac.checkStock.repository.member.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMemberByEmail(String email) {
        List<Member> memberList = memberRepository.findByEmail(email);
        if (memberList.isEmpty()) {
            //TODO : 미가입
        } else if (memberList.size() > 1) {
            //TODO : 중복가입
        }
        return memberList.get(0);
    }

    public boolean isPasswordCorrect(Member member) {
        Member searchedMember = getMemberByEmail(member.getEmail());
        return searchedMember.getPassword().equals(member.getPassword());
    }

}
