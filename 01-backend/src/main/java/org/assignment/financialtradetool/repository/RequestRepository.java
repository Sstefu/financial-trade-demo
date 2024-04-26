package org.assignment.financialtradetool.repository;

import org.assignment.financialtradetool.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sstefan
 * Date: 4/22/2024
 * Project: 01-backend
 */
@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    @Query("select distinct r.email from Request as r")
    List<String> findDistinctEmails();

    List<Request> findRequestByEmail(String email);
}
