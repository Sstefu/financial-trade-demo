package org.assignment.financialtradetool.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assignment.financialtradetool.constants.Status;
import org.assignment.financialtradetool.domain.Bank;
import org.assignment.financialtradetool.domain.Exporter;
import org.assignment.financialtradetool.domain.Request;
import org.assignment.financialtradetool.domain.TransactionHistory;
import org.assignment.financialtradetool.dto.BankDTO;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.repository.BankRepository;
import org.assignment.financialtradetool.repository.RequestRepository;
import org.assignment.financialtradetool.services.BankService;
import org.assignment.financialtradetool.services.TransactionHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    private final ModelMapper mapper;
    private final TransactionHistoryService transactionHistoryService;

    @Override
    public List<RequestDTO> findRequestsByBankName(String bankName) {
        List<Bank> listOfBanks = bankRepository.findBankByName(bankName);

        return listOfBanks.stream()
                .map(Bank::getRequest)
                .map(request -> mapper.map(request, RequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BankDTO> findBankByRequestId(Long id) {
        Bank bank = bankRepository.findBankByRequestId(id).orElse(null);
        return Optional.ofNullable(bank == null ? null :
                mapper.map(bank, BankDTO.class));
    }

    @Transactional
    @Override
    public Optional<BankDTO> updateBankDetailsBasedOnRequestId(Long id, BankDTO approvalDTO) {
        AtomicReference<Optional<BankDTO>> atomicReference = new AtomicReference<>();
        bankRepository.findBankByRequestId(id).ifPresentOrElse(foundBank -> {
            foundBank.setIsApproved(approvalDTO.getIsApproved());
            foundBank.setAccountNumber(approvalDTO.getAccountNumber());
            if (StringUtils.hasText(approvalDTO.getComments())) {
                foundBank.setComments(approvalDTO.getComments());
                foundBank.setStatus(Status.BANK_DECLINED.toString());
                foundBank.getRequest().setRequestStatus(Status.REJECT.toString());
            }else {
                foundBank.setStatus(Status.BANK_APPROVED.toString());
            }

            transactionHistoryService.addNewHistory(foundBank.getRequest(),foundBank.getStatus())
                            .ifPresent((transactionHistory) -> log.info("Transaction history added on Bank status update"));

            atomicReference.set(Optional.of(
                    mapper.map(bankRepository.save(foundBank),
                            BankDTO.class)
            ));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }



    @Override
    public List<RequestDTO> findRequestsByBankNameDeclinedByExporter(String bankName) {
        List<Bank> listOfBanks = bankRepository.findBankByName(bankName);
        return listOfBanks.stream()
                .map(Bank::getRequest)
                .map(Request::getExporter)
                .filter(exporter -> exporter.getStatus() != null &&
                        exporter.getStatus().equals(Status.EXPORTER_DECLINED.toString()))
                .map(Exporter::getRequest)
                .map(request -> mapper.map(request, RequestDTO.class))
                .collect(Collectors.toList());
    }

}
