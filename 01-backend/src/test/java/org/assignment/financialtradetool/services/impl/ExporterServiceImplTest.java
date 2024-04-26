package org.assignment.financialtradetool.services.impl;

import org.assignment.financialtradetool.dto.ExporterDTO;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.services.ExporterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
@SpringBootTest
class ExporterServiceImplTest {
    @Autowired
    ExporterService exporterService;

    @Test
    void test_get_bank_validated_request_for_specific_exporter() {
        //given
        String exporterName = "exporter01";

        //when
        List<RequestDTO> list = exporterService.findRequestsBankApprovalAndExporterName(exporterName);

        //then
        assertThat(list).isNotEmpty();
    }
    @Test
    void test_update_prices_with_vat_for_approved_requests() {
        //when
        List<ExporterDTO> list = exporterService.updatedPricesIncludingVat();

        //then
        assertThat(list).isNotEmpty();
        System.out.println(list);
    }
}