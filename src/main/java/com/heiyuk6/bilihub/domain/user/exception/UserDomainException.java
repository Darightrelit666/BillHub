package com.heiyuk6.bilihub.domain.user.exception;

/**
 * 领域层专用的业务异常
 */
public class UserDomainException extends RuntimeException {
    public UserDomainException(String message) {
        super(message);
    }
}
