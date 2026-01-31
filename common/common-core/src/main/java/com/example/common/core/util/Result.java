
package com.example.common.core.util;

import com.example.common.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldNameConstants
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;

	@Setter
	private T data;

	public T getData(){
		return data;
	}

	public static <T> Result<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, null);
	}

	public static <T> Result<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, null);
	}

	public static <T> Result<T> ok(T data, String msg) {
		return restResult(data, CommonConstants.SUCCESS, msg);
	}

	public static <T> Result<T> failed() {
		return restResult(null, CommonConstants.FAIL, null);
	}

	public static <T> Result<T> failed(String msg) {
		return restResult(null, CommonConstants.FAIL, msg);
	}

	public static <T> Result<T> failed(T data) {
		return restResult(data, CommonConstants.FAIL, null);
	}

	public static <T> Result<T> failed(T data, String msg) {
		return restResult(data, CommonConstants.FAIL, msg);
	}

	public static <T> Result<T> restResult(T data, int code, String msg) {
		Result<T> apiResult = new Result<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

}
