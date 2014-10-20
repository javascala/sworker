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

public class SensitiveWordsFilter implements Filter {

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

        // TODO:具体逻辑处理
        // 获取评论并屏蔽关键字
        String comment = req.getParameter("comment");
        comment = comment.replace("A", "***");
        // 重新设置参数
        req.setAttribute("comment", comment);
        // 继续执行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
