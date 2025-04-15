package com.example.data.bootstrap;

import com.example.data.domain.CareProvider;
import com.example.data.domain.HealthIssue;
import com.example.data.domain.Patient;
import com.example.data.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Tester implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final HealthIssueRepository healthIssueRepository;
    private final MedicalEncounterRepository medicalEncounterRepository;
    private final HealthServiceRepository healthServiceRepository;
    private final CareProviderRepository careProviderRepository;

    public Tester(PatientRepository patientRepository,
                  HealthIssueRepository healthIssueRepository,
                  MedicalEncounterRepository medicalEncounterRepository,
                  HealthServiceRepository healthServiceRepository,
                  CareProviderRepository careProviderRepository) {
        this.patientRepository = patientRepository;
        this.healthIssueRepository = healthIssueRepository;
        this.medicalEncounterRepository = medicalEncounterRepository;
        this.healthServiceRepository = healthServiceRepository;
        this.careProviderRepository = careProviderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Long patientId = 1L;

        System.out.println("=== Query 1: Health Issues for Patient ID " + patientId + " ===");
        List<HealthIssue> allHealthIssues = healthIssueRepository.findAllByPatientId(patientId);
        System.out.println("All Health Issues:");
        allHealthIssues.forEach(issue ->
                System.out.println(" - " + issue.getType() + " (ID: " + issue.getId() + ")")
        );

        List<HealthIssue> issuesWithServices = healthIssueRepository.findWithServiceByPatientId(patientId);
        System.out.println("Health Issues with at least one Health Service:");
        issuesWithServices.forEach(issue ->
                System.out.println(" - " + issue.getType() + " (ID: " + issue.getId() + ")")
        );

        LocalDate queryDate = LocalDate.now();
        System.out.println("\n=== Query 2: Patients with Encounter on " + queryDate + " ===");
        List<Patient> patientsByDate = medicalEncounterRepository.findPatientsByEncounterDate(queryDate);
        patientsByDate.forEach(patient ->
                System.out.println(" - " + patient.getName() + " (ID: " + patient.getId() + ")")
        );

        String careProviderName = "Dr. John Doe";
        System.out.println("\n=== Query 3: Patients for CareProvider " + careProviderName + " ===");
        List<Patient> patientsByProvider = medicalEncounterRepository.findPatientsByCareProviderName(careProviderName);
        patientsByProvider.forEach(patient ->
                System.out.println(" - " + patient.getName() + " (ID: " + patient.getId() + ")")
        );

        String healthIssueType = "Hypertension";
        System.out.println("\n=== Query 4: CareProviders for Health Issue: " + healthIssueType + " ===");
        List<CareProvider> providers = healthServiceRepository.findCareProvidersByHealthIssueType(healthIssueType);
        providers.forEach(provider ->
                System.out.println(" - " + provider.getName() + " (Specialty: " + provider.getSpecialty() + ")")
        );
    }
}
