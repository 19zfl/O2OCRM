package com.o2ocrm.basic.config.exception;

import com.o2ocrm.basic.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName:GlobalExceptionHandler
 * @Description: 全局异常处理
 * @Author:zfl19
 * @CreateDate:2024/3/27 22:04
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /** 请求异常 */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public AjaxResult handlerException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error("不支持'" + e.getMethod() + "'请求");
    }

    /** 拦截未知，运行时异常 */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult notFound(RuntimeException e) {
        log.error("运行时异常：", e);
        return AjaxResult.error("运行时异常：" + e.getMessage());
    }

    /** 系统异常 */
    @ExceptionHandler(Exception.class)
    public AjaxResult handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error("服务器失联了，请联系:19aleiya@gmail.com");
    }

    /** 校验异常 */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult exceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage += fieldError.getDefaultMessage() + "!";
        }
        return AjaxResult.error(errorMessage);
    }

    /** 校验异常 */
    @ExceptionHandler(value = BindException.class)
    public AjaxResult validaExceptionHandler(BindException e) {
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage += fieldError.getDefaultMessage() + "!";
        }
        return AjaxResult.error(errorMessage);
    }

    /** 校验异常 */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public AjaxResult ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> violation = iterator.next();
            msgList.add(violation.getMessageTemplate());
        }
        return AjaxResult.error(String.join(",", msgList));
    }

}
