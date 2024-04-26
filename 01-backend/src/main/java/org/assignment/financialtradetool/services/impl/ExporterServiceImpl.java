package org.assignment.financialtradetool.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assignment.financialtradetool.constants.Status;
import org.assignment.financialtradetool.domain.Exporter;
import org.assignment.financialtradetool.dto.ExporterDTO;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.repository.ExporterRepository;
import org.assignment.financialtradetool.services.ExporterService;
import org.assignment.financialtradetool.services.TransactionHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class ExporterServiceImpl implements ExporterService {
    private final ExporterRepository exporterRepository;
    private final TransactionHistoryService transactionHistoryService;
    private final ModelMapper mapper;

    @Override
    public List<RequestDTO> findRequestsBankApprovalAndExporterName(String name) {
        List<Exporter> exporterList = exporterRepository.findExporterByNameAndStatusIsNull(name);
        return exporterList.stream()
                .map(Exporter::getRequest)
                .filter(request -> request.getBank().getIsApproved())
                .map(request -> mapper.map(request,RequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ExporterDTO> updateExporterDetailsById(Long id, ExporterDTO exporterDTO) {
        AtomicReference<Optional<ExporterDTO>> atomicReference = new AtomicReference<>();
        exporterRepository.findById(id).ifPresentOrElse(foundExporter -> {
            foundExporter.setPrice(exporterDTO.getPrice());
            foundExporter.setComments(exporterDTO.getComments());
            foundExporter.setIsApproved(exporterDTO.getIsApproved());
            foundExporter.setEstimationDate(exporterDTO.getEstimationDate());
            if (exporterDTO.getIsApproved()){
                foundExporter.setStatus(Status.EXPORTER_APPROVED.toString());
                foundExporter.getRequest().setRequestStatus(Status.APPROVED.toString());
            }else {
                foundExporter.setStatus(Status.EXPORTER_DECLINED.toString());
                foundExporter.getRequest().setRequestStatus(Status.PENDING_BANK_APPROVAL.toString());
            }

            transactionHistoryService.addNewHistory(foundExporter.getRequest(),foundExporter.getStatus())
                    .ifPresent((transactionHistory) -> log.info("Transaction history added on Exporter status update"));

            atomicReference.set(
                    Optional.of(
                            mapper.map(exporterRepository.save(foundExporter),
                                    ExporterDTO.class)));
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public Optional<ExporterDTO> getExporterByRequestId(Long id) {
        Exporter exporter = exporterRepository.findExporterByRequestId(id).orElse(null);
        return Optional.ofNullable(exporter == null ? null :
                mapper.map(exporter, ExporterDTO.class));
    }

    @Override
    public List<ExporterDTO> updatedPricesIncludingVat() {
        List<Exporter> exporters = exporterRepository.findExporterByRequestRequestStatus(Status.APPROVED.toString());
        exporters.forEach(entity -> {
            entity.setPriceWithVAT(updatePriceVatIncluded(entity.getPrice()));
        });
        List<Exporter> updatedPrices = exporterRepository.saveAll(exporters);

        return updatedPrices.stream()
                .map(exporter -> mapper.map(exporter,ExporterDTO.class))
                .collect(Collectors.toList());
    }

    private BigDecimal updatePriceVatIncluded(BigDecimal price) {
        BigDecimal vat = price.multiply(new BigDecimal("0.20"));
        return price.add(vat);
    }
}
