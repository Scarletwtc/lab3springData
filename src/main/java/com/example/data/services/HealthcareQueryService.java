package com.example.data.services;

import com.example.data.domain.CareProvider;
import com.example.data.domain.HealthIssue;
import com.example.data.domain.Patient;
import java.time.LocalDate;
import java.util.List;

public interface HealthcareQueryService {

    // Retrieve all health issues for a given patient ID.
    List<HealthIssue> getHealthIssuesByPatientId(Long patientId);

    // Retrieve health issues for a given patient that have at least one health service.
    List<HealthIssue> getHealthIssuesWithServiceByPatientId(Long patientId);

    // Retrieve distinct patients for a given medical encounter date.
    List<Patient> getPatientsByEncounterDate(LocalDate date);

    // Retrieve distinct patients seen by a care provider with the given name.
    List<Patient> getPatientsByCareProviderName(String providerName);

    // Retrieve distinct care providers who performed a health service for the specified health issue type.
    List<CareProvider> getCareProvidersByHealthIssueType(String issueType);
}
