package com.foodordering.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean{

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");

        if("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            if(authHeader==null || !authHeader.startsWith("Bearer ")){
                throw new ServletException("Missing or invalid Authorization header");
            }
            final String token=authHeader.substring(7);

            try{
                final Claims claims= Jwts.parser()
                        .setSigningKey("secretkey")
                        .parseClaimsJws(token)
                        .getBody();
                request.setAttribute("claims",claims);
            }catch(Exception e){
                throw new ServletException("Invalid token");
            }
            filterChain.doFilter(req,res);
        }
    }
}
