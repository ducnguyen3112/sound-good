package com.starscream.soundgood.dtos.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    T result;
    String message;
    List<String> errors;

    public static <T> ApiResponse<T> success(T result) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> invalid(List<String> errors) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setErrors(errors);
        return response;
    }

    public static <T> ApiResponse<T> responseMessage(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMessage(message);
        return response;
    }
}
