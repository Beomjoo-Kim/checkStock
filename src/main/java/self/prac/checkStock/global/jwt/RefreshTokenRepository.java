package self.prac.checkStock.global.jwt;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class RefreshTokenRepository {
    private final RedisTemplate<String, RefreshToken> redisTemplate;

    public void save(RefreshToken refreshToken) {
        redisTemplate.opsForValue().set(refreshToken.getRefreshToken(), refreshToken);
    }
    public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
        RefreshToken token = redisTemplate.opsForValue().get(refreshToken);
        return Optional.ofNullable(token);
    }

    public void delete(RefreshToken refreshToken) {
        redisTemplate.delete(refreshToken.getRefreshToken());
    }
}
