package org.assignment.financialtradetool.services;

import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.dto.RequestTransactionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
public interface RequestService {
    RequestDTO saveNewRequest(RequestTransactionDTO transactionDTO);
    Optional<RequestDTO> getRequestById(Long id);
    Optional<RequestDTO> updateRequestById(Long id, RequestDTO dto);

    List<String> getRequestsAllEmails();

    List<RequestDTO> getRequestsByEmail(String email);
}
