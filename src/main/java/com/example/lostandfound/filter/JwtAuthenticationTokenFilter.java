package com.example.lostandfound.filter;

import com.example.lostandfound.utils.JwtUtil;
import com.example.lostandfound.utils.RedisCache;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    RedisCache redisCache;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Claims parseToken;
        String token = request.getHeader("Token");
        response.addHeader("Access-Control-Expose-Headers","status");
        if ("/lostandfound/login/".equals(request.getRequestURI())){
            filterChain.doFilter(request, response);
            return;
        }
        if (token == null) {
            response.addIntHeader("status", 403);
            filterChain.doFilter(request, response);
        } else {
            try {
                parseToken = JwtUtil.parseJWT(token);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            String subject = parseToken.getSubject();
            Pattern pattern = Pattern.compile("email=(.*?)\\)");
            Matcher matcher = pattern.matcher(subject);

            String email;
            if (matcher.find()) {
                email = matcher.group(1);
                String jwt = redisCache.getCacheObject("token-user-" + email);
                log.info("token:"+token);
                log.info("jwt:"+jwt);
                if (token.isEmpty() || !token.equals(jwt)) {
                    log.info("认证失败====");
                    response.addIntHeader("status", 403);
                    filterChain.doFilter(request, response);
                }else {
                    log.info("认证成功");
                    filterChain.doFilter(request, response);
                }
            } else {
                log.info("认证失败====");
                response.setIntHeader("status", 403);
                filterChain.doFilter(request, response);
            }
        }

    }
}