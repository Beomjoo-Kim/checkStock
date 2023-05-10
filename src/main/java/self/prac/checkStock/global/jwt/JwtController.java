package self.prac.checkStock.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import self.prac.checkStock.global.domain.SignInResponse;
import self.prac.checkStock.global.domain.UserDto;

@Controller
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class JwtController {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/refresh")
    public SignInResponse regenerateAccessToken(@RequestParam String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> new IllegalArgumentException("no such refreshToken"));
        UserDto userDto = new UserDto(refreshToken);
        String accessToken = jwtUtil.generateToken(userDto);
        String newRefreshToken = jwtUtil.generateRefreshToken();

        SignInResponse signInResponse = new SignInResponse(accessToken, newRefreshToken);
        return signInResponse;
    }
}
