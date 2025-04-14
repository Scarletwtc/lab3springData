package com.example.data.bootstrap;

import com.example.data.domain.*;
import com.example.data.repositories.PatientRepository;
import com.example.data.services.HealthcareQueryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class Tester implements CommandLineRunner {

    private final HealthcareQueryService queryService;
    private final PatientRepository patientRepository;

    public Tester(HealthcareQueryService queryService,
                       PatientRepository patientRepository) {
        this.queryService = queryService;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // ------------------------------------
        // 1) Given Patient ID -> Retrieve all health issues
        //    and health issues with at least one health service.
        // ------------------------------------
        // For example, use patient1's ID (assume its ID is 1)
        Long patientId = 1L;
        System.out.println("=== Query 1: Health Issues for Patient ID " + patientId + " ===");
        List<HealthIssue> allHealthIssues = queryService.getHealthIssuesByPatientId(patientId);
        System.out.println("All Health Issues:");
        allHealthIssues.forEach(issue ->
                System.out.println(" - " + issue.getType() + " (ID: " + issue.getId() + ")")
        );

        List<HealthIssue> issuesWithServices = queryService.getHealthIssuesWithServiceByPatientId(patientId);
        System.out.println("Health Issues with at least one Health Service:");
        issuesWithServices.forEach(issue ->
                System.out.println(" - " + issue.getType() + " (ID: " + issue.getId() + ")")
        );

        // ------------------------------------
        // 2) Given the date -> Retrieve all patients with a medical encounter on that date.
        // ------------------------------------
        LocalDate queryDate = LocalDate.now();  // for example, current date
        System.out.println("\n=== Query 2: Patients with Encounter on " + queryDate + " ===");
        List<Patient> patientsByDate = queryService.getPatientsByEncounterDate(queryDate);
        patientsByDate.forEach(patient ->
                System.out.println(" - " + patient.getName() + " (ID: " + patient.getId() + ")")
        );

        // ------------------------------------
        // 3) Given a CareProvider name -> Retrieve all his/her patients.
        // ------------------------------------
        String careProviderName = "Dr. John Doe";
        System.out.println("\n=== Query 3: Patients for CareProvider " + careProviderName + " ===");
        List<Patient> patientsByProvider = queryService.getPatientsByCareProviderName(careProviderName);
        patientsByProvider.forEach(patient ->
                System.out.println(" - " + patient.getName() + " (ID: " + patient.getId() + ")")
        );

        // ------------------------------------
        // 4) Given health issue type -> Retrieve all care providers
        //    who performed a health service for that health issue.
        // ------------------------------------
        String healthIssueType = "Hypertension";
        System.out.println("\n=== Query 4: CareProviders for Health Issue: " + healthIssueType + " ===");
        List<CareProvider> providers = queryService.getCareProvidersByHealthIssueType(healthIssueType);
        providers.forEach(provider ->
                System.out.println(" - " + provider.getName() + " (Specialty: " + provider.getSpecialty() + ")")
        );
    }
}
