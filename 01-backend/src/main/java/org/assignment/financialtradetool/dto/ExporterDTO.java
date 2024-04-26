package org.assignment.financialtradetool.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExporterDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal priceWithVAT;
    private Boolean isApproved;
    private String comments;
    private String status;
    private Date estimationDate;

}
