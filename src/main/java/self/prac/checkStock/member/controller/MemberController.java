package self.prac.checkStock.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.prac.checkStock.global.constants.SessionConst;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.member.domain.MemberStatus;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;
import self.prac.checkStock.member.repository.MemberRepository;
import self.prac.checkStock.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/signIn")
    public ResponseEntity<Member> signIn(@RequestBody Member member, HttpServletRequest request) {
        memberService.signIn(member);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/signOut")
    @ResponseStatus(HttpStatus.OK)
    public void signOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<Member> signUp(HttpServletRequest request, @RequestBody Member member) {
        memberService.signUp(member);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> members(HttpServletRequest request) {
        if (request.getSession().getAttribute(SessionConst.LOGIN_ADMIN) != null) {
            return ResponseEntity.ok(memberRepository.findAll());
        } else {
            throw new CustomRuntimeException(CustomErrorCodes.NO_AUTH);
        }
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> member(@PathVariable Long memberId, HttpServletRequest request) {
        if (request.getSession().getAttribute(SessionConst.LOGIN_ADMIN) != null) {
            return ResponseEntity.ok(memberRepository.findOne(memberId));
        } else {
            throw new CustomRuntimeException(CustomErrorCodes.NO_AUTH);
        }
    }

    @PostMapping("/kick/{memberId}")
    public Member kickMember(@PathVariable Long id) {
        Member member = memberRepository.findOne(id);
        memberService.modifyStatus(member, MemberStatus.BANNED);
        return member;
    }

    @PostMapping("/modify")
    public Member modifyMember(@RequestBody Member member) {
        return null;
    }
}
