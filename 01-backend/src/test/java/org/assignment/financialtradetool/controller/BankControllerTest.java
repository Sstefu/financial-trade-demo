package org.assignment.financialtradetool.controller;

import org.assignment.financialtradetool.dto.BankDTO;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
@SpringBootTest
class BankControllerTest {

    @Autowired
    BankController bankController;
    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    void test_get_request_by_name() {
        //given
        String name = "bank01";

        //when
        List<RequestDTO> listOfRequests = bankController.getRequestsByBankName(name);

        //then
        assertThat(listOfRequests.size()).isGreaterThan(1);
    }
    @Test
    void test_get_bank_by_request_id() {
        //given
        Long requestId = 6L;

        //when
        BankDTO dto = bankController.getBankByRequestId(requestId);

        //then
        assertThat(dto).isNotNull();

    }

    @Test
    void test_update_bank_by_request_id() {
        //given
        Long requestId = 6L;
        BankDTO updatedBank = BankDTO.builder()
                .accountNumber("acountUpdated-2222")
                .isApproved(true)
                .build();

        //when
        ResponseEntity<HttpStatus> response = bankController.updateBankDetailsByRequestId(requestId, updatedBank);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
    }
}