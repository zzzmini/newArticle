package com.my.articles.api.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseMessage {
    String message;
}
