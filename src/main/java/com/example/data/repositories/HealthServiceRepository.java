package com.example.data.repositories;

import com.example.data.domain.CareProvider;
import com.example.data.domain.HealthService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HealthServiceRepository extends CrudRepository<HealthService, Long> {

    // Retrieve all care providers who performed a health service for a given health issue type.
    // Since HealthService does not have a direct careProvider field, we join to MedicalEncounter (which does)
    @Query("SELECT DISTINCT me.careProvider FROM HealthService hs JOIN hs.medicalEncounter me WHERE hs.healthIssue.type = :issueType")
    List<CareProvider> findCareProvidersByHealthIssueType(@Param("issueType") String issueType);
}
