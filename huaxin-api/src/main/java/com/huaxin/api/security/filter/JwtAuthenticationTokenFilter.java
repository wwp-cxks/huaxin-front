package com.huaxin.api.security.filter;

import com.huaxin.api.security.config.JwtSecurityConfig;
import com.huaxin.api.security.util.JwtTokenUtils;
import com.huaxin.api.security.util.SpringContextHolderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 该类继承OncePerRequestFilter，顾名思义，它能够确保在一次请求中只通过一次filter
 * 该类使用JwtTokenUtils工具类进行token校验
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtils jwtTokenUtils;

    public JwtAuthenticationTokenFilter(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        JwtSecurityConfig jwtSecurityConfig = SpringContextHolderUtils.getBean(JwtSecurityConfig.class);
        String requestRri = httpServletRequest.getRequestURI(); // 获取请求地址
        //获取request token
        String token = null;
        String bearerToken = httpServletRequest.getHeader(jwtSecurityConfig.getHeader()); // 获取请求头中的授权信息
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtSecurityConfig.getTokenStartWith())) {
            token = bearerToken.substring(jwtSecurityConfig.getTokenStartWith().length());
        }

        if (StringUtils.hasText(token) && jwtTokenUtils.validateToken(token)) {
            Authentication authentication = jwtTokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestRri);
        } else {
            log.debug("no valid JWT token found, uri: {}", requestRri);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
