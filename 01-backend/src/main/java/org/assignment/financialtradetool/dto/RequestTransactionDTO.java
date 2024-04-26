package org.assignment.financialtradetool.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import static org.assignment.financialtradetool.constants.ErrorMessages.*;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@Data
@Builder
public class RequestTransactionDTO {

    private Long id;
    @NotBlank(message = BLANK_MESSAGE)
    @NotNull(message = NULL_MESSAGE)
    private String deliveryAddress;

    @NotBlank(message = BLANK_MESSAGE)
    @NotNull(message = NULL_MESSAGE)
    private String phone;

    @Email(message = INVALID_EMAIL)
    @NotBlank(message = BLANK_MESSAGE)
    @NotNull(message = NULL_MESSAGE)
    private String email;

    @NotBlank(message = BLANK_MESSAGE)
    @NotNull(message = NULL_MESSAGE)
    private String bankName;

    @NotBlank(message = BLANK_MESSAGE)
    @NotNull(message = NULL_MESSAGE)
    private String bankAccountNumber;

    @NotBlank(message = BLANK_MESSAGE)
    @NotNull(message = NULL_MESSAGE)
    private String exporterName;

    @NotBlank(message = BLANK_MESSAGE)
    @NotNull(message = NULL_MESSAGE)
    private String goods;

    @NotNull(message = NULL_MESSAGE)
    private Integer amount;



}
