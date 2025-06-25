package com.mohan.shopping_app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private String message;
    private String error;
    private String url;
    private Map<String, String> validationErrors;

    public ErrorMessage(String message, String error, String url) {
        this.message = message;
        this.error = error;
        this.url = url;
    }
}
