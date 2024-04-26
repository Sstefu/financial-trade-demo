package org.assignment.financialtradetool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    private Long id;
    private String deliveryAddress;
    private String phone;
    private String email;
    private String goods;
    private Integer amount;
    private String requestStatus;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
}
