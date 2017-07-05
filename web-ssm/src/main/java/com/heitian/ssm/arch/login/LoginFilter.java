package com.heitian.ssm.arch.login;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 功能：判断用户是否登录
 * 注册账户或修改账户密码可以直接在过滤器跳转
 * Author:Yang yanli
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = httpRequest.getSession();
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String uri = httpRequest.getRequestURI();
            uri = uri.replace(httpRequest.getContextPath(), "");
            Principal userDetail = (Principal) session.getAttribute("principal");
            if (userDetail == null) {
                if (uri.contains("/loginHandler/forwardLogin")) {
                    chain.doFilter(request, response);
                } else if (uri.contains("loginHandler/signPage")) {
                    //跳转到注册账户界面
                    httpRequest.getRequestDispatcher("/loginHandler/signPage").forward(httpRequest, httpResponse);
                } else if (uri.contains("/loginHandler/loginInfo")) {
                    chain.doFilter(request, response);
                } else if (uri.startsWith("/resources/")) {
                    chain.doFilter(request, response);
                } else if (uri.startsWith("/captcha.jsp")) {
                    chain.doFilter(request, response);
                } else if (uri.startsWith("/loginHandler/signAccout")) {
                    //点击保存，保存账户信息
                    httpRequest.getRequestDispatcher("/loginHandler/signAccout").forward(httpRequest, httpResponse);
                }else if (uri.startsWith("/phrase/getPhraseLibByParamJsonp")) {
                    chain.doFilter(request, response);
                }else {
                    httpRequest.setAttribute("login", "登录！");
                    httpRequest.getRequestDispatcher("/loginHandler/forwardLogin").forward(httpRequest, httpResponse);
                }
            } else {
                chain.doFilter(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

}
