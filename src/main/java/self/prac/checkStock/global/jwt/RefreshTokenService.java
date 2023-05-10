package self.prac.checkStock.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.prac.checkStock.global.domain.UserDto;

import javax.transaction.Transactional;

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

    //TODO : application 실행 시 redis 초기화에 대해 고민.
}
