package org.assignment.financialtradetool.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.assignment.financialtradetool.dto.BankDTO;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.exceptions.ex.NotFoundException;
import org.assignment.financialtradetool.services.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
@RestController
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;
    public static final String BANK_PATH = "/api/v1/bank";
    public static final String BANK_PATH_BY_NAME = BANK_PATH + "/name/{bankName}";
    public static final String BANK_PATH_BY_NAME_DECLINED_REQUESTS_BY_EXPORTER = BANK_PATH + "/name/declined/{bankName}";
    public static final String BANK_PATH_BY_REQUEST_ID = BANK_PATH + "/request/{requestId}";

    @GetMapping(BANK_PATH_BY_NAME)
    public List<RequestDTO> getRequestsByBankName(@PathVariable("bankName") String bankName) {
        return bankService.findRequestsByBankName(bankName);
    }
    @GetMapping(BANK_PATH_BY_NAME_DECLINED_REQUESTS_BY_EXPORTER)
    public List<RequestDTO> getRequestsNotValidatedByExporter(@PathVariable("bankName") String bankName) {
        return bankService.findRequestsByBankNameDeclinedByExporter(bankName);
    }
    @GetMapping(BANK_PATH_BY_REQUEST_ID)
    public BankDTO getBankByRequestId(@PathVariable("requestId") Long requestId){
        return bankService.findBankByRequestId(requestId).orElseThrow(NotFoundException::new);
    }
    @PutMapping(BANK_PATH_BY_REQUEST_ID)
    public ResponseEntity<HttpStatus> updateBankDetailsByRequestId(@PathVariable("requestId") Long requestId,
                                                                   @Valid @RequestBody BankDTO bankDTO) {
        if (bankService.updateBankDetailsBasedOnRequestId(requestId,bankDTO).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
