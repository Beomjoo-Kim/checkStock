package self.prac.checkStock.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import self.prac.checkStock.global.domain.SignInResponse;
import self.prac.checkStock.global.jwt.JwtUtil;
import self.prac.checkStock.global.jwt.RefreshToken;
import self.prac.checkStock.global.jwt.RefreshTokenRepository;
import self.prac.checkStock.global.jwt.RefreshTokenService;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.global.domain.UserDto;
import self.prac.checkStock.member.domain.MemberStatus;
import self.prac.checkStock.member.repository.MemberRepository;
import self.prac.checkStock.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @GetMapping("/signIn")
    public SignInResponse signIn(@RequestBody Member member) {
        Member signInMember = memberService.signIn(member);
        UserDto userDto = new UserDto(signInMember.getId(), signInMember.getName(), signInMember.getEmail(), signInMember.getRole());

        //redis save
        String accessToken = jwtUtil.generateToken(userDto);
        String refreshToken = jwtUtil.generateRefreshToken();
        refreshTokenService.saveTokenInfo(userDto, refreshToken);

        return new SignInResponse(accessToken, refreshToken);
    }

    @PostMapping("/signUp")
    public Member signUp(@RequestBody Member member) {
        memberService.signUp(member);
        return member;
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> members(HttpServletRequest request) {
        return ResponseEntity.ok(memberRepository.findAll());
    }

    @GetMapping("/{memberId}")
    public Member member(@PathVariable Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("no member searched"));
    }

    @Transactional
    @PostMapping("/kick/{memberId}")
    public Member kickMember(@PathVariable Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no member searched"));
        memberService.modifyStatus(member, MemberStatus.BANNED);
        return member;
    }

    @Transactional
    @PostMapping("/modify")
    public Member modifyMember(@RequestBody Member member) {
        if (member.getName().isEmpty() || member.getPassword().isEmpty() || member.getPhone().isEmpty()) {
            throw new IllegalArgumentException("need new info");
        }

        Member modifiedMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new IllegalArgumentException("no member searched"));

        modifiedMember.modifyMember(member.getName(), member.getPassword(), member.getPhone());
        return modifiedMember;
    }

    @Transactional
    @PostMapping("/withdraw")
    public void requestWithdraw(@RequestBody Member member) {
        Member searchedMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new IllegalArgumentException("no member searched"));
        searchedMember.requestWithdraw();
    }
}
