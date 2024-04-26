package org.assignment.financialtradetool.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.assignment.financialtradetool.dto.ExporterDTO;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.exceptions.ex.NotFoundException;
import org.assignment.financialtradetool.services.ExporterService;
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
public class ExporterController {
    private final ExporterService exporterService;

    public static final String EXPORTER_PATH = "/api/v1/exporter";
    public static final String EXPORTER_PATH_GET_EXPORTER_BY_REQUEST_ID = EXPORTER_PATH + "/request/{requestId}";
    public static final String EXPORTER_PATH_REQUESTS_APPROVED_BY_NAME = EXPORTER_PATH + "/approved/{exporterName}";
    public static final String EXPORTER_PATH_BY_ID = EXPORTER_PATH + "/{exporterId}";

    @GetMapping(EXPORTER_PATH_REQUESTS_APPROVED_BY_NAME)
    public List<RequestDTO> getApprovedRequests(@PathVariable("exporterName") String exporterName) {
        return exporterService.findRequestsBankApprovalAndExporterName(exporterName);
    }
    @PutMapping(EXPORTER_PATH_BY_ID)
    public ResponseEntity<HttpStatus> updateExporterDetails(@PathVariable("exporterId") Long id,
                                                            @Valid @RequestBody ExporterDTO exporterDTO) {

        if (exporterService.updateExporterDetailsById(id, exporterDTO).isEmpty()){
            throw new NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(EXPORTER_PATH_GET_EXPORTER_BY_REQUEST_ID)
    public ExporterDTO getExporter(@PathVariable("requestId") Long id) {
        return exporterService.getExporterByRequestId(id).orElseThrow(NotFoundException::new);
    }
}
