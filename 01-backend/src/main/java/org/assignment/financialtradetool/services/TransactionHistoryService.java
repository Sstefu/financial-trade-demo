package org.assignment.financialtradetool.services;

import org.assignment.financialtradetool.domain.Request;
import org.assignment.financialtradetool.domain.TransactionHistory;
import org.assignment.financialtradetool.dto.TransactionHistoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by sstefan
 * Date: 4/26/2024
 * Project: 01-backend
 */
public interface TransactionHistoryService {
    Optional<TransactionHistory> addNewHistory(Request request, String action);

    List<TransactionHistoryDTO> getTransactionHistoryForRequestId(Long id);
}
