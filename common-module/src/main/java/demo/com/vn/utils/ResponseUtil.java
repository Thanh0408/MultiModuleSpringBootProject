package demo.com.vn.utils;

import demo.com.vn.dto.response.ApiResponse;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class ResponseUtil {
    public static <T> ApiResponse<T> success(T data, String message, String path) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setErrors(null);
        response.setErrorCode(0); // No application-level error, success scenario
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(path);
        return response;
    }

    public static <T> ApiResponse<T> error(List<String> errors, String message, int errorCode, String path) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setData(null);
        response.setErrors(errors);
        response.setErrorCode(errorCode);  // Application-specific error code
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(path);
        return response;
    }

    public static <T> ApiResponse<T> error(String error, String message, int errorCode, String path) {
        return error(Collections.singletonList(error), message, errorCode, path);
    }
}
