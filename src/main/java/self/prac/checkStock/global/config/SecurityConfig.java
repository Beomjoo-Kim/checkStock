package self.prac.checkStock.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import self.prac.checkStock.global.filter.JwtAuthenticationFilter;
import self.prac.checkStock.global.utils.JwtUtil;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
        http.headers().frameOptions().disable();
        //ROLE
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/member/signUp").permitAll()
                .antMatchers("/api/member/signIn").permitAll()
//                .antMatchers("/api/member/**").hasRole("ADMIN")
                .antMatchers("/api/order/**").hasRole("MEMBER")
                //TODO : admin 권한관리
                .antMatchers("/api/member/members").hasRole("ADMIN")
                .antMatchers("/api/member/**").hasRole("MEMBER")
        ;

        //jwt
        http
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class).authorizeRequests()
        ;

        return http.build();
    }

}

