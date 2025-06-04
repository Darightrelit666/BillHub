package com.heiyuk6.bilihub.common.result;

import lombok.Data;

/**
 * 统一的 REST 响应格式
 */
@Data
public class ApiResponse<T> {
    private int code;      // 0 表示成功，非 0 表示特定错误码
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> r = new ApiResponse<>();
        r.setCode(0);
        r.setMessage("success");
        r.setData(data);
        return r;
    }

    public static <T> ApiResponse<T> fail(int code, String message) {
        ApiResponse<T> r = new ApiResponse<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(null);
        return r;
    }
}

