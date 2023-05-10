package self.prac.checkStock.global.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60*60*24*3) //timeToLive 는 sec 단위
@AllArgsConstructor
public class RefreshToken {
    @Id
    private String refreshToken;

    private Long id;
    private String email;
    private String name;
    private String role;
}
