package org.assignment.financialtradetool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by sstefan
 * Date: 4/26/2024
 * Project: 01-backend
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistoryDTO {
    private LocalDateTime statusDate;
    private String status;
}
