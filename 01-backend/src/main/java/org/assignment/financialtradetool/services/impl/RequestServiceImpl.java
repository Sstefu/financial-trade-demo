package org.assignment.financialtradetool.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assignment.financialtradetool.constants.Status;
import org.assignment.financialtradetool.domain.Bank;
import org.assignment.financialtradetool.domain.Exporter;
import org.assignment.financialtradetool.domain.Request;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.dto.RequestTransactionDTO;
import org.assignment.financialtradetool.repository.RequestRepository;
import org.assignment.financialtradetool.services.RequestService;
import org.assignment.financialtradetool.services.TransactionHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RequestServiceImpl implements RequestService {
    private final ModelMapper modelMapper;
    private final RequestRepository requestRepository;
    private final TransactionHistoryService transactionHistoryService;
    @Override
    public RequestDTO saveNewRequest(RequestTransactionDTO transactionDTO) {
        Request transaction = Request.builder()
                .deliveryAddress(transactionDTO.getDeliveryAddress())
                .email(transactionDTO.getEmail())
                .phone(transactionDTO.getPhone())
                .goods(transactionDTO.getGoods())
                .amount(transactionDTO.getAmount())
                .requestStatus(Status.PENDING_BANK_APPROVAL.toString())
                .bank( Bank.builder()
                        .accountNumber(transactionDTO.getBankAccountNumber())
                        .isApproved(false)
                        .name(transactionDTO.getBankName())
                        .status(Status.REQUEST_INITIALIZED.toString())
                        .build())
                .exporter(Exporter.builder()
                        .name(transactionDTO.getExporterName())
                        .isApproved(false)
                        .build())
                .build();
        Request savedRequest = requestRepository.save(transaction);

        transactionHistoryService.addNewHistory(savedRequest,Status.REQUEST_INITIALIZED.toString())
                .ifPresent((transactionHistory) -> log.info("Transaction history added on new request update"));

        return modelMapper.map(requestRepository.save(transaction),
                RequestDTO.class);
    }

    @Override
    public Optional<RequestDTO> getRequestById(Long id) {
        Request  foundRequest = requestRepository.findById(id).orElse(null);
        return Optional.ofNullable( foundRequest == null ? null :
                modelMapper.map(foundRequest,RequestDTO.class));
    }

    @Override
    public Optional<RequestDTO> updateRequestById(Long id, RequestDTO dto) {
        AtomicReference<Optional<RequestDTO>> atomicReference = new AtomicReference<>();
        requestRepository.findById(id).ifPresentOrElse(foundRequest -> {
            foundRequest.setAmount(dto.getAmount());
            foundRequest.setGoods(dto.getGoods());
            foundRequest.setRequestStatus(Status.REQUEST_INITIALIZED.toString());
            atomicReference.set(
                    Optional.of(
                            modelMapper.map(requestRepository.save(foundRequest),
                                    RequestDTO.class)
                    )
            );
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public List<String> getRequestsAllEmails() {
        return requestRepository.findDistinctEmails();
    }

    @Override
    public List<RequestDTO> getRequestsByEmail(String email) {
        List<Request> requests = requestRepository.findRequestByEmail(email);
        return requests.stream()
                .map(request -> modelMapper.map(request,RequestDTO.class))
                .collect(Collectors.toList());
    }
}
