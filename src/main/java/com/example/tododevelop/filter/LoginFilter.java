package com.example.tododevelop.filter;

import com.example.tododevelop.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/" ,"/users/login", "/users/regi"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse SerVletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {

            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {

                throw new RuntimeException("로그인 안됨");
            }


        }

        filterChain.doFilter(servletRequest, SerVletResponse);

    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}
