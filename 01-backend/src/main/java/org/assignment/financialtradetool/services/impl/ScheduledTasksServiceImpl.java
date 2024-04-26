package org.assignment.financialtradetool.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assignment.financialtradetool.dto.ExporterDTO;
import org.assignment.financialtradetool.services.ExporterService;
import org.assignment.financialtradetool.services.ScheduledTasksService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sstefan
 * Date: 4/26/2024
 * Project: 01-backend
 */
@Slf4j
@Component
@RequiredArgsConstructor
@EnableScheduling
public class ScheduledTasksServiceImpl implements ScheduledTasksService {
    private final ExporterService exporterService;

    // Execute daily at midnight
    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void updatePricesWithVatOnApprovedRequests() {
        List<ExporterDTO> dtos = exporterService.updatedPricesIncludingVat();
        if(dtos.isEmpty()){
            throw new RuntimeException("There is are no approved requests, to recalculate the price including VAT");
        }
        else {
            log.info("Nr of requests updated: " + dtos.size());
        };
    }
}
