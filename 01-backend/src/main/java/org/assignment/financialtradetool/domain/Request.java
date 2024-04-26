package org.assignment.financialtradetool.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Request {
    @Builder
    public Request(Long id, String deliveryAddress, String phone, String email,
                   String goods, Integer amount,String requestStatus,Bank bank, Exporter exporter){
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.email = email;
        this.goods = goods;
        this.amount = amount;
        this.requestStatus = requestStatus;
        this.setBank(bank);
        this.setExporter(exporter);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deliveryAddress;
    private String phone;
    private String email;
    private String requestStatus;
    private String goods;
    private Integer amount;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "request")
    private Bank bank;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "request")
    private Exporter exporter;

    @OneToMany(mappedBy = "request")
    private List<TransactionHistory> transactionHistoryList;

    public void setBank(Bank bank) {
        this.bank = bank;
        bank.setRequest(this);
    }

    public void setExporter(Exporter exporter) {
        this.exporter = exporter;
        exporter.setRequest(this);
    }

}
