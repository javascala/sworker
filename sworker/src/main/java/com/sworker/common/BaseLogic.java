package com.sworker.common;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseLogic {

    // 异常处理
    @ExceptionHandler
    public String exception(HttpServletRequest request, Exception e) {  
          
        //添加自己的异常处理逻辑，如日志记录　　　
        request.setAttribute("exceptionMessage", e.getMessage());  
          
        // TODO：根据不同的异常类型进行不同处理
        if(e instanceof SQLException) 
            return "testerror";   
        else
            return "error";  
    }  
}
