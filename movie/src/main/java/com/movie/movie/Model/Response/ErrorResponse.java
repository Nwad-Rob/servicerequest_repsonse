package com.movie.movie.Model.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    String errorCode;
    String errorMessage;
    String applicationMessage;
}
