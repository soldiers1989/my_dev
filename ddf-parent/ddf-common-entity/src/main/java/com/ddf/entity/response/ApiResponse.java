package com.ddf.entity.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.validation.FieldError;

public class ApiResponse<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String result;
	
	private String message;
	
	private List<FieldError> fieldErrors;
	
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}


	public void setResult(String result) {
		this.result = result;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	
	public static <T> ApiResponse<T> success(T data){
		ApiResponse<T> apiResponse = new ApiResponse<T>();
		apiResponse.setMessage(ApiResponseResult.SUCCESS.getMessage());
		apiResponse.setResult(ApiResponseResult.SUCCESS.name());
		apiResponse.setData(data);
		return apiResponse;
	}
	
	public static <T> ApiResponse<T> fail(ApiResponseResult result){
		ApiResponse<T> apiResponse = new ApiResponse<T>();
		apiResponse.setMessage(result.getMessage());
		apiResponse.setResult(result.name());
		apiResponse.setData(null);
		return apiResponse;
	}

	public static <T> ApiResponse<T> fail4fieldError(List<FieldError> fieldErrors){
		ApiResponseResult result = ApiResponseResult.COMMON_PARAM_ERROR;
		ApiResponse<T> apiResponse = new ApiResponse<T>();
		apiResponse.setMessage(result.getMessage());
		apiResponse.setResult(result.name());
		apiResponse.setFieldErrors(fieldErrors);
		apiResponse.setData(null);
		return apiResponse;
	}
	
	public static <T> ApiResponse<T> buildDefinedMessage(String message){
		ApiResponse<T> apiResponse = new ApiResponse<T>();
		apiResponse.setMessage(message);
		apiResponse.setResult(ApiResponseResult.BUSS_ERROR.toString());
		apiResponse.setData(null);
		return apiResponse;
	}

	
	
}
