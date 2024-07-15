package com.lilmoon.http.logging;

import com.lilmoon.http.configuration.LoggingProperties;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoggingFilter implements Filter {

    private final LoggingProperties properties;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logRequest((HttpServletRequest) servletRequest);

        filterChain.doFilter(servletRequest,servletResponse);

        logResponse((HttpServletRequest) servletRequest,(HttpServletResponse) servletResponse);
    }

    private void logResponse(HttpServletRequest request, HttpServletResponse response) {
        doLog("Request {} : status {}",request.getServletPath(),response.getStatus());
    }


    private void logRequest(HttpServletRequest request) throws IOException {
        doLog("Request {}",request.getServletPath());

        if (properties.isLogBody()){
            try (BufferedInputStream inputStream = new BufferedInputStream(request.getInputStream())){
               String body = new String( inputStream.readAllBytes());
               doLog("Request body {}",body);
            }
        }
    }

    private void doLog(String logMessage, Object ... params){
        log.atLevel(properties.getLogLevel()).log(logMessage,params);
    }
}
