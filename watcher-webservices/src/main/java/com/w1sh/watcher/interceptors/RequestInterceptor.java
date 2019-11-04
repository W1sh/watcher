package com.w1sh.watcher.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        if (request.getRequestURI().equals("/error")) return false;
        long start = System.currentTimeMillis();
        log.info("Received request from origin {} ", request.getAttribute("origin"));
        request.setAttribute("startTime", start);
        //if returned false, we need to make sure 'response' is sent
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        //logger.info("Processing request..");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        long start = (Long) request.getAttribute("startTime");
        log.info("Completed request in {} ms", (System.currentTimeMillis() - start));
    }
}
