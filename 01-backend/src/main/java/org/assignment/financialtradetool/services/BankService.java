package org.assignment.financialtradetool.services;

import org.assignment.financialtradetool.dto.BankDTO;
import org.assignment.financialtradetool.dto.RequestDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
public interface BankService {
    List<RequestDTO> findRequestsByBankName(String bankName);
    Optional<BankDTO> findBankByRequestId(Long id);
    Optional<BankDTO> updateBankDetailsBasedOnRequestId(Long id, BankDTO approvalDTO);


    List<RequestDTO> findRequestsByBankNameDeclinedByExporter(String bankName);
}
