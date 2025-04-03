package com.yybm8.mylist.collection;

import com.yybm8.mylist.dao.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Glob {
    // 全局异常处理
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {

        //TODO 记录日志
        //TODO 通知开发人员
        return Result.fail();
    }
}
