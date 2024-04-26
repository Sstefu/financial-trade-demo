package org.assignment.financialtradetool.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exporter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private BigDecimal priceWithVAT;
    private Boolean isApproved;
    private String comments;
    private String status;
    private Date estimationDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

}
