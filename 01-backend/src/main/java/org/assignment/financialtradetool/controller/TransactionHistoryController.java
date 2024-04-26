package org.assignment.financialtradetool.controller;

import lombok.RequiredArgsConstructor;
import org.assignment.financialtradetool.dto.TransactionHistoryDTO;
import org.assignment.financialtradetool.services.TransactionHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sstefan
 * Date: 4/26/2024
 * Project: 01-backend
 */
@RestController
@RequiredArgsConstructor
public class TransactionHistoryController {
    private final TransactionHistoryService transactionHistoryService;
    public static final String HISTORY_PATH = "/api/history";
    public static final String HISTORY_PATH_ON_REQUEST_ID = HISTORY_PATH + "/{requestId}";


    @GetMapping(HISTORY_PATH_ON_REQUEST_ID)
    public List<TransactionHistoryDTO> getTransactionHistoryOnRequestId(@PathVariable("requestId") Long id) {
        return transactionHistoryService.getTransactionHistoryForRequestId(id);
    }


}
