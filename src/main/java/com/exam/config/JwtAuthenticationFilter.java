package com.exam.config;

import com.exam.helper.JwtUtil;
import com.exam.service.impl.UserDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken=null;

        // get username and token
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")){
            // yes token is valid
            try{
                jwtToken= requestTokenHeader.substring(7);
                username= jwtUtil.extractUsername(jwtToken);

            }catch (ExpiredJwtException ex){
                System.out.println("Token Expired");
                ex.printStackTrace();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            System.out.println("Invalid Token");
        }

        // validate
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailService.loadUserByUsername(username);

            if(jwtUtil.validateToken(jwtToken,userDetails)){
                // token is valid

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(
                        usernamePasswordAuthenticationToken
                );
            }else{
                System.out.println("Token is not valid");
            }
        }
        filterChain.doFilter(request,response);
    }


}
