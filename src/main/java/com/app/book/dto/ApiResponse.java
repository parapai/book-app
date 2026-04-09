package com.app.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> extends BaseResponse {
    
	private Data data;
	
    
    public ApiResponse() {
        super();
    }

    public ApiResponse(String statusCode, String statusDesc, Data data) {
        super(); 
        this.data = data;
    }

    public ApiResponse(String statusCode, String statusDesc, String message, Data data) {
        super(statusCode, statusDesc, message); 
        this.data = data;
    }

    public static <T> ApiResponse<T> success(Data data, String message) {
        return new ApiResponse<T>("200", "Success", message, data);
    }
    
    public static <T> ApiResponse<T> error(String statusCode, String statusDesc, String message) {
        return new ApiResponse<T>(statusCode, statusDesc, message, null);
    }
}