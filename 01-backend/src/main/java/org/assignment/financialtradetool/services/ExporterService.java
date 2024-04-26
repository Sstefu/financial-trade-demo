package org.assignment.financialtradetool.services;

import org.assignment.financialtradetool.dto.ExporterDTO;
import org.assignment.financialtradetool.dto.RequestDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
public interface ExporterService {
    List<RequestDTO> findRequestsBankApprovalAndExporterName(String name);
    Optional<ExporterDTO> updateExporterDetailsById(Long id, ExporterDTO exporterDTO);
    Optional<ExporterDTO> getExporterByRequestId(Long id);
    List<ExporterDTO> updatedPricesIncludingVat();
}
