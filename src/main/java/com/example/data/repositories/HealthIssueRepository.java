package com.example.data.repositories;

import com.example.data.domain.HealthIssue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HealthIssueRepository extends CrudRepository<HealthIssue, Long> {

    // All health issues for a given patient ID.
    @Query("SELECT hi FROM HealthIssue hi WHERE hi.patient.id = :patientId")
    List<HealthIssue> findAllByPatientId(@Param("patientId") Long patientId);

    //Health issues for a given patient that have at least one health service.
    @Query("SELECT hi FROM HealthIssue hi WHERE hi.patient.id = :patientId AND SIZE(hi.healthServices) > 0")
    List<HealthIssue> findWithServiceByPatientId(@Param("patientId") Long patientId);
}
