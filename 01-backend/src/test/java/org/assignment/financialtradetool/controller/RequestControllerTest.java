package org.assignment.financialtradetool.controller;

import org.assignment.financialtradetool.domain.Request;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.dto.RequestTransactionDTO;
import org.assignment.financialtradetool.exceptions.ex.NotFoundException;
import org.assignment.financialtradetool.repository.RequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
@SpringBootTest
class RequestControllerTest {
    @Autowired
    RequestController requestController;

    @Autowired
    RequestRepository requestRepository;

    @Transactional
    @Rollback
    @Test
    void test_create_new_request() {
        //given
        RequestTransactionDTO request = RequestTransactionDTO.builder()
                .deliveryAddress("test address")
                .phone("9999999")
                .email("test@mail.com")
                .bankName("bank01")
                .bankAccountNumber("accountR-zzz")
                .exporterName("exporter01")
                .goods("iron")
                .amount(100)
                .build();

        //when
        ResponseEntity<RequestDTO> response = requestController.handleNewRequest(request);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(response.getHeaders().getLocation()).isNotNull();

        String[] locationId = response.getHeaders().getLocation().getPath().split("/");
        Long savedId = Long.valueOf(locationId[4]);

        Request savedRequest = requestRepository.findById(savedId).get();
        assertThat(savedRequest).isNotNull();
    }

    @Test
    void test_find_request_by_id_success() {
        //given
        Long id = 1L;

        //when
        RequestDTO requestDTO = requestController.getRequestById(id);

        //then
        assertNotNull(requestDTO);
    }

    @Test
    void test_find_request_by_id_not_found() {
        assertThrows(NotFoundException.class, () -> requestController.getRequestById(200L));
    }
}