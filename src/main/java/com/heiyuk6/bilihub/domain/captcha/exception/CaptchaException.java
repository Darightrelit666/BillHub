package com.heiyuk6.bilihub.domain.captcha.exception;

/**
 * 验证码相关的业务异常
 */
public class CaptchaException extends RuntimeException {
    public CaptchaException(String message) {
        super(message);
    }
}
