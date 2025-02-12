package com.example.tododevelop.filter;

import com.example.tododevelop.config.Const;
import com.example.tododevelop.exception.UnauthorizedException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/" ,"/users/login", "/users/regi"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse SerVletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) SerVletResponse;


        try {
            if (!isWhiteList(requestURI)) {

                HttpSession session = httpRequest.getSession(false);

                if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {

                    throw new RuntimeException("User Only");
                }
            }
            filterChain.doFilter(servletRequest, SerVletResponse);

        }catch (UnauthorizedException e){
            httpRequest.setAttribute("exception", e);
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }




    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}
