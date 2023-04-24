package self.prac.checkStock.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import self.prac.checkStock.global.constants.SessionConst;
import self.prac.checkStock.global.utils.JwtUtil;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.member.domain.MemberDto;
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
    private final JwtUtil jwtUtil;

    @GetMapping("/signIn")
    public String signIn(@RequestBody Member member) {
        Member signInMember = memberService.signIn(member);
        MemberDto memberDto = new MemberDto(signInMember.getId(), signInMember.getName(), signInMember.getEmail(), signInMember.getRole());
        return jwtUtil.generateToken(memberDto);
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
    public ResponseEntity<Member> signUp(@RequestBody Member member) {
        memberService.signUp(member);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> members(HttpServletRequest request) {
        return ResponseEntity.ok(memberRepository.findAll());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> member(@PathVariable Long memberId, HttpServletRequest request) {
            return ResponseEntity.ok(memberRepository.findOne(memberId));
    }

    @PostMapping("/kick/{memberId}")
    public ResponseEntity<Member> kickMember(@PathVariable Long id) {
        Member member = memberRepository.findOne(id);
        memberService.modifyStatus(member, MemberStatus.BANNED);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/modify")
    public Member modifyMember(@RequestBody Member member) {
        Member modifiedMember = memberRepository.findOne(member.getId());
        //modifiedMember 에 set
        return null;
    }
}
