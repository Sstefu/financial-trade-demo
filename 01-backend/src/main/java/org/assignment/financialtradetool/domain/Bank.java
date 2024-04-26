package org.assignment.financialtradetool.domain;

import jakarta.persistence.*;
import lombok.*;

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
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String accountNumber;
    private Boolean isApproved;
    private String status;
    private String comments;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

}
