package com.heitian.ssm.arch.login;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能：
 */
public class SessionUtil {

    public Principal getUserInfo(){
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Principal principal = (Principal)httpRequest.getSession().getAttribute("principal");
        return principal;
    }
}
