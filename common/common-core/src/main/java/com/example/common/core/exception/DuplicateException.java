package com.example.common.core.exception;

/**
 *  数据重复异常（409）
 */
public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
