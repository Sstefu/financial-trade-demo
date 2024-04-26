package org.assignment.financialtradetool.repository;

import org.assignment.financialtradetool.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {
    List<Bank> findBankByName(String bankName);
    Optional<Bank> findBankByRequestId(Long id);

}
