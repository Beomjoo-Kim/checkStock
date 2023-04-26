package self.prac.checkStock.global.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import self.prac.checkStock.global.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO
        String token = jwtUtil.extractToken((HttpServletRequest) request);
        if (token!=null && !jwtUtil.isTokenExpired(token)) {
            Authentication authentication = null;
            switch (jwtUtil.extractRole(token)) {
                case "ROLE_ADMIN":
                    authentication = jwtUtil.getAdminAuthentication(token);
                    break;
                case "ROLE_MEMBER":
                    authentication = jwtUtil.getMemberAuthentication(token);
                    break;
                default:
                    break;
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }


}
