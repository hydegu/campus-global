
package com.example.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * 授权拒绝异常类
 */
@NoArgsConstructor
public class DeniedAuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DeniedAuthException(String message) {
		super(message);
	}

	public DeniedAuthException(Throwable cause) {
		super(cause);
	}

	public DeniedAuthException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeniedAuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
