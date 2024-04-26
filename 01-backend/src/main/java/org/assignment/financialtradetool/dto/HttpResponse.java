package org.assignment.financialtradetool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponse {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+2")
    private LocalDateTime timeStamp;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private String reason;
    private String message;

    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message) {
        this.timeStamp = LocalDateTime.now();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.reason = reason;
        this.message = message;
    }
}
