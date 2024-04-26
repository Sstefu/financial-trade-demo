package org.assignment.financialtradetool.services.impl;

import lombok.RequiredArgsConstructor;
import org.assignment.financialtradetool.domain.Request;
import org.assignment.financialtradetool.domain.TransactionHistory;
import org.assignment.financialtradetool.dto.TransactionHistoryDTO;
import org.assignment.financialtradetool.repository.TransactionHistoryRepository;
import org.assignment.financialtradetool.services.TransactionHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sstefan
 * Date: 4/26/2024
 * Project: 01-backend
 */
@Service
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<TransactionHistory> addNewHistory(Request request, String status) {
        TransactionHistory transactionHistory = transactionHistoryRepository.save(
                TransactionHistory.builder()
                        .status(status)
                        .request(request)
                        .build()
        );
        return Optional.of(transactionHistory);
    }

    @Override
    public List<TransactionHistoryDTO> getTransactionHistoryForRequestId(Long id) {
        List<TransactionHistory> list = transactionHistoryRepository.findTransactionHistoriesByRequestId(id);

        return list.stream().
                map(entity -> mapper.map(entity,TransactionHistoryDTO.class))
                .collect(Collectors.toList());
    }
}
