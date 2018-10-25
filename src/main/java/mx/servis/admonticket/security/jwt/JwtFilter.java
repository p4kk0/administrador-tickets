package mx.servis.admonticket.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean{
	
	private JwtTokenProvider jwtTokerProvider;
	

    public JwtFilter(JwtTokenProvider jwtTokerProvider) {
        this.jwtTokerProvider = jwtTokerProvider;
    }
	
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = jwtTokerProvider.resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwt) && jwtTokerProvider.validateToken(jwt)) {
            Authentication authentication = jwtTokerProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
