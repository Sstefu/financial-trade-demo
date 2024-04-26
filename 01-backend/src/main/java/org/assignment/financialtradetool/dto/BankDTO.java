package org.assignment.financialtradetool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private Long id;
    private String name;
    private String accountNumber;
    private String status;
    private String comments;
    private Boolean isApproved;

}
