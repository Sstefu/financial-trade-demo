package org.assignment.financialtradetool.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.dto.RequestTransactionDTO;
import org.assignment.financialtradetool.exceptions.ex.NotFoundException;
import org.assignment.financialtradetool.services.RequestService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@RestController
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    public static final String REQUEST_PATH = "/api/v1/request";
    public static final String REQUEST_PATH_BY_ID = REQUEST_PATH + "/{id}";
    public static final String REQUEST_PATH_ALL_EMAILS = REQUEST_PATH +"/mails";
    private static final String REQUEST_PATH_BY_EMAIL = REQUEST_PATH + "/by/{email}";
    @PostMapping(REQUEST_PATH)
    public ResponseEntity<RequestDTO> handleNewRequest(@Valid @RequestBody RequestTransactionDTO transactionDTO) {
        RequestDTO savedRequest = requestService.saveNewRequest(transactionDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", REQUEST_PATH + "/" + savedRequest.getId().toString());

        return new ResponseEntity<>(savedRequest, headers, HttpStatus.CREATED);
    }

    @GetMapping(REQUEST_PATH_BY_ID)
    public RequestDTO getRequestById(@PathVariable("id") Long id) {
        return requestService.getRequestById(id).orElseThrow(NotFoundException::new);
    }
    @GetMapping(REQUEST_PATH_ALL_EMAILS)
    public List<String> getAllEmails(){
        return requestService.getRequestsAllEmails();
    }

    @GetMapping(REQUEST_PATH_BY_EMAIL)
    public List<RequestDTO> getRequestsByEmail(@PathVariable("email") String email) {
        return requestService.getRequestsByEmail(email);
    }
}
