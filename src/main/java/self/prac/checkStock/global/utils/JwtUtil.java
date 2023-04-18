package self.prac.checkStock.global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import self.prac.checkStock.member.domain.MemberDto;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtil {
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME =  30 * 60 * 1000L; //30min

    public String generateToken(MemberDto memberDto) {
        Map<String, Object> claims = new HashMap<>();

        log.info("memberEmail : " + memberDto.getEmail());
        log.info("memberName : " + memberDto.getName());

        claims.put("email", memberDto.getId());
        claims.put("name", memberDto.getName());
        return createToken(claims, memberDto.getId());
    }

    private String createToken(Map<String, Object> claims, Long id) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(KEY)
                .compact();
    }

    public boolean validateToken(String token, MemberDto memberDto) {
        String id = extractMemberId(token);
        return id.equals(memberDto.getId()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    public String extractMemberId(String token) {
        return extractClaim(token, claims -> claims.get("id", String.class));
    }

    public String extractMemberName(String token) {
        return extractClaim(token, claims -> claims.get("name", String.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
    }

    public String getSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

}
