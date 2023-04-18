package self.prac.checkStock.global.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import self.prac.checkStock.global.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/security")
public class SecurityController {
    // security test controller

    private final JwtUtil jwtUtil;

    @GetMapping("/get/subject")
    public ResponseEntity<Map<String, Object>> getSubject(@RequestParam(value = "token") String token) {
        String subject = jwtUtil.getSubject(token);
        Map<String, Object> result = new HashMap<>();
        result.put("subject", subject);
        return ResponseEntity.ok(result);
    }
}
