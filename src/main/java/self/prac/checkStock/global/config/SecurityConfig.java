package self.prac.checkStock.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import self.prac.checkStock.global.utils.JwtUtil;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebSecurityConfigurer<WebSecurity> {

    private final JwtUtil jwtUtil;

    public void configure(HttpSecurity http) throws Exception {
        // HttpSecurity 설정
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 인증 관련 설정
    }

    public void configure(WebSecurity web) throws Exception {
        // web 리소스 관련 설정
    }

    public void init(WebSecurity web) throws Exception {
        // init 메서드 구현
    }
}

