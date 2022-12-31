package com.fiec.DrConnect.Configuration;

import com.fiec.DrConnect.models.entities.User;
import com.fiec.DrConnect.Utils.JwtTokenUtil;
import com.fiec.DrConnect.models.enums.UserRole;
import com.fiec.DrConnect.models.repositories.DoctorRepository;
import com.fiec.DrConnect.models.repositories.PatientRepository;
import com.fiec.DrConnect.models.repositories.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token

        List<String> excludedUrls = Arrays.asList("/auth/signIn");

        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        String pathUri = request.getRequestURI();
        if (!excludedUrls.contains(pathUri) && requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            jwtToken = requestTokenHeader.substring(7);
            try {
                email = jwtTokenUtil.getUsernameFromToken(jwtToken);
                UserRole userRole = jwtTokenUtil.getRoleFromToken(jwtToken);

                User user = userRepository.findUserByEmail(email).orElseThrow();
                user.setRole(userRole);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }
        filterChain.doFilter(request, response);
    }

}
