package com.findoutmyloan.application.security.jwt;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.security.enums.JwtConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token=getToken(request);
        if (StringUtils.hasText(token)) {
            boolean isValid=jwtTokenGenerator.validateToken(token);
            if (isValid) {
                Long userId=jwtTokenGenerator.findUserIdByToken(token);
                UserDetails userDetails=jwtUserDetailsService.loadUserByUsername(userId);
                if (userDetails!=null) {
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Authorize only our system's token
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String fullToken=request.getHeader("Authorization");
        String token=null;
        if (StringUtils.hasText(fullToken)) {
            String bearer=JwtConstant.BEARER.getConstant();
            if (fullToken.startsWith(bearer)) {
                token=fullToken.substring(bearer.length());
            }
        }
        return token;
    }
}
