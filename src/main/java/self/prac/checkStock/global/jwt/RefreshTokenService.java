package self.prac.checkStock.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import self.prac.checkStock.global.domain.UserDto;
import self.prac.checkStock.global.error.exception.CustomErrorCodes;
import self.prac.checkStock.global.error.exception.CustomRuntimeException;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private JwtUtil jwtUtil;

    @Transactional
    public void saveTokenInfo(UserDto userDto, String refreshToken) {
        refreshTokenRepository.save(new RefreshToken(refreshToken, userDto.getId(), userDto.getEmail(), userDto.getName(), userDto.getRole()));
    }

    @Transactional
    public void removeRefreshToken(String refreshToken) {
        refreshTokenRepository.findByRefreshToken(refreshToken)
                .ifPresent(token -> refreshTokenRepository.delete(token));
    }

    public RefreshToken getRefreshToken(@RequestBody Map<String, Object> jsonObject) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(Objects.toString(jsonObject.get("token")))
                .orElseThrow(() -> new CustomRuntimeException(CustomErrorCodes.TOKEN_NOT_FOUND));
        if (jwtUtil.isTokenExpired(refreshToken.getRefreshToken())) {
            throw new CustomRuntimeException(CustomErrorCodes.TOKEN_EXPIRED);
        }
        removeRefreshToken(refreshToken.getRefreshToken());
        return refreshToken;
    }

    //TODO : application 실행 시 redis 초기화에 대해 고민.
}
