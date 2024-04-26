package org.assignment.financialtradetool.repository;

import org.assignment.financialtradetool.domain.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sstefan
 * Date: 4/26/2024
 * Project: 01-backend
 */
@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Long> {
    List<TransactionHistory> findTransactionHistoriesByRequestId(Long id);
}

