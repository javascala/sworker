package com.sworker.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init LoginFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        // 把ServletRequest和ServletResponse转换成真正的类型
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        // 由于web.xml中设置Filter过滤全部请求，可以排除不需要过滤的url
     // 不过滤的uri  
        String[] notFilter = new String[] { "login.html", "index.html" };  
        
     // 请求的uri  
        String requestURI = req.getRequestURI();  
        for (String s : notFilter) {  
            if (requestURI.indexOf(s) != -1) {  
                // 如果uri中包含不过滤的uri，则不进行过滤  
                //doFilter = false;  
                break;  
            }  
        }  

        // TODO:具体逻辑处理,权限判断等等
        // 判断用户是否登录，进行页面的处理
        if (null == session.getAttribute("user")) {
            // 未登录用户，重定向到登录页面
            ((HttpServletResponse) response).sendRedirect("login.jsp");
            return;
        } else {
            // 已登录用户，允许访问
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
