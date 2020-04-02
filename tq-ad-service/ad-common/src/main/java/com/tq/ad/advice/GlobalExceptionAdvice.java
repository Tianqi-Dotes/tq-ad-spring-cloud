package com.tq.ad.advice;

import com.tq.ad.exception.AdException;
import com.tq.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest request, AdException exception){

        CommonResponse<String> response=new CommonResponse<>(-1,"business error!");
        response.setData(exception.getMessage());

        return response;
    }

}
