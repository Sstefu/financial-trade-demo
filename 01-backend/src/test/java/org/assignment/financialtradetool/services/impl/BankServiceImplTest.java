package org.assignment.financialtradetool.services.impl;

import org.assignment.financialtradetool.domain.Bank;
import org.assignment.financialtradetool.domain.Request;
import org.assignment.financialtradetool.dto.RequestDTO;
import org.assignment.financialtradetool.repository.BankRepository;
import org.assignment.financialtradetool.services.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
@SpringBootTest
class BankServiceImplTest {
    @Autowired
    BankService bankService;
    @Autowired
    BankRepository bankRepository;

    @Test
    void test_get_list_of_Requests() {
        //given
        String name = "bank01";

        //when
        List<RequestDTO> list = bankService.findRequestsByBankName("bank01");

        //then
        assertThat(list.size()).isGreaterThan(1);
    }
    @Test
    void get_requests_based_on_bank_name_and_declined_by_exporter(){
        String name = "bank01";
//        List<Bank> listOfBanks bankRepository.;
        //when
        List<RequestDTO> list = bankService.findRequestsByBankNameDeclinedByExporter("bank01");

        //then
        System.out.println(list);
    }
}