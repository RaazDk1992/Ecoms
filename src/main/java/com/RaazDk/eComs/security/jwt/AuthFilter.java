package com.RaazDk.eComs.security.jwt;

import com.RaazDk.eComs.security.services.EcomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class AuthFilter  extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    EcomUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            try{
                String jwt = parseJwt(request);
                if(jwt !=null && jwtUtils.validateJwtToken(jwt)){
                    String username = jwtUtils.getUserNameFromToken(jwt);
                    UserDetails details = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }


            }catch (Exception e){
            logger.debug("Error setting auth details {}",e.getMessage());
            }

            filterChain.doFilter(request,response);
    }

    private String parseJwt(HttpServletRequest request) {
        String jwt = jwtUtils.getJwtFromHeader(request);
        logger.debug("Retrieved jwt{}",jwt);
        return jwt;
    }
}
