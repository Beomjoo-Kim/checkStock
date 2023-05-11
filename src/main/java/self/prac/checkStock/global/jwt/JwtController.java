package self.prac.checkStock.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import self.prac.checkStock.global.domain.SignInResponse;
import self.prac.checkStock.global.domain.UserDto;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class JwtController {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/refresh")
    public SignInResponse regenerateAccessToken(@RequestBody Map<String, Object> jsonObject) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(Objects.toString(jsonObject.get("token")))
                .orElseThrow(() -> new IllegalArgumentException("no such refreshToken"));
        UserDto userDto = new UserDto(refreshToken);
        String accessToken = jwtUtil.generateToken(userDto);
        String newRefreshToken = jwtUtil.generateRefreshToken();

        SignInResponse signInResponse = new SignInResponse(accessToken, newRefreshToken);
        return signInResponse;
    }
}
