package self.prac.checkStock.global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import self.prac.checkStock.admin.domain.Admin;
import self.prac.checkStock.admin.service.AdminService;
import self.prac.checkStock.member.domain.Member;
import self.prac.checkStock.global.domain.UserDto;
import self.prac.checkStock.member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    //TODO : refreshToken 구현

    private final MemberService memberService;
    private final AdminService adminService;

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME =  30 * 60 * 1000L; //30min

    public String generateToken(UserDto userDto) {
        Map<String, Object> claims = new HashMap<>();

        log.info("memberEmail : " + userDto.getEmail());
        log.info("memberName : " + userDto.getName());
        log.info("role : " + userDto.getRole());

        claims.put("email", userDto.getEmail());
        claims.put("name", userDto.getName());
        claims.put("role", userDto.getRole());
        return createToken(claims, userDto.getId());
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
                .setHeaderParam("typ", "JWT")
                .compact();
    }

    public boolean validateToken(String token, UserDto userDto) {
        String id = extractUserEmail(token);
        return id.equals(userDto.getEmail()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    public String extractUserName(String token) {
        return extractClaim(token, claims -> claims.get("name", String.class));
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
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

    public String extractToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    public String extractToken(String rawToken) {
        return rawToken.replaceAll("Bearer ","");
    }

    private Authentication getMemberAuthentication(String token) {
        Member member = memberService.getMemberByEmail(extractUserEmail(token));
        return new UsernamePasswordAuthenticationToken(member, "", member.getAuthorities());
    }

    private Authentication getAdminAuthentication(String token) {
        Admin admin = adminService.findAdminByEmail(extractUserEmail(token));
        return new UsernamePasswordAuthenticationToken(admin, "", admin.getAuthorities());
    }

    public Authentication getUserAuthentication(String token) {
        String role = extractRole(token);
        switch (role) {
            case "ROLE_ADMIN":
                return getAdminAuthentication(token);
            case "ROLE_MEMBER":
                return getMemberAuthentication(token);
            default:
                return null;
        }
    }
}
