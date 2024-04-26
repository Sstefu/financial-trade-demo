package org.assignment.financialtradetool.repository;

import org.aspectj.apache.bcel.classfile.Module;
import org.assignment.financialtradetool.domain.Exporter;
import org.assignment.financialtradetool.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
@Repository
public interface ExporterRepository extends JpaRepository<Exporter, Long> {
    List<Exporter> findExporterByNameAndStatusIsNull(String name);
    Optional<Exporter> findExporterByRequestId(Long id);
    List<Exporter> findExporterByRequestRequestStatus(String requestStatus);

}
