package com.heiyuk6.bilihub.common.exception;

import com.heiyuk6.bilihub.common.result.ApiResponse;
import com.heiyuk6.bilihub.domain.user.exception.UserDomainException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，将所有领域异常和未知异常转为统一格式
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有 UserDomainException
     */
    @ExceptionHandler(UserDomainException.class)
    public ApiResponse<?> handleUserDomain(UserDomainException ex) {
        // code=1001 代表用户相关的业务异常
        return ApiResponse.fail(1001, ex.getMessage());
    }

    /**
     * 处理所有其他未捕获的 Exception
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleOther(Exception ex) {
        ex.printStackTrace();
        return ApiResponse.fail(500, "服务器内部错误");
    }
}
