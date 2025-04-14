package com.example.data.bootstrap;

import com.example.data.domain.*;
import com.example.data.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final HealthIssueRepository healthIssueRepository;
    private final MedicalEncounterRepository medicalEncounterRepository;
    private final HealthServiceRepository healthServiceRepository;
    private final CareProviderRepository careProviderRepository;

    public DataLoader(PatientRepository patientRepository,
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
        // Create CareProviders
        CareProvider cp1 = new CareProvider("Dr. John Doe", "General Medicine");
        CareProvider cp2 = new CareProvider("Dr. Jane Smith", "Cardiology");
        careProviderRepository.save(cp1);
        careProviderRepository.save(cp2);

        // Create Patients
        Patient patient1 = new Patient("Alice Smith");
        Patient patient2 = new Patient("Bob Johnson");
        Patient patient3 = new Patient("Charlie Brown");
        Patient patient4 = new Patient("Dana White");
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
        patientRepository.save(patient4);

        // Create HealthIssues for each patient
        HealthIssue hi1 = new HealthIssue("Hypertension");
        HealthIssue hi2 = new HealthIssue("Diabetes");
        HealthIssue hi3 = new HealthIssue("Asthma");
        HealthIssue hi4 = new HealthIssue("High Cholesterol");
        HealthIssue hi5 = new HealthIssue("Arthritis");

        // Assign health issues to patients
        patient1.addHealthIssue(hi1);  // Alice has Hypertension
        patient1.addHealthIssue(hi2);  // Alice also has Diabetes
        patient2.addHealthIssue(hi3);  // Bob has Asthma
        patient3.addHealthIssue(hi4);  // Charlie has High Cholesterol
        patient4.addHealthIssue(hi5);  // Dana has Arthritis

        healthIssueRepository.save(hi1);
        healthIssueRepository.save(hi2);
        healthIssueRepository.save(hi3);
        healthIssueRepository.save(hi4);
        healthIssueRepository.save(hi5);

        // Create MedicalEncounters on different dates
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate twoDaysAgo = today.minusDays(2);

        MedicalEncounter enc1 = new MedicalEncounter(yesterday);
        MedicalEncounter enc2 = new MedicalEncounter(today);
        MedicalEncounter enc3 = new MedicalEncounter(twoDaysAgo);
        MedicalEncounter enc4 = new MedicalEncounter(today);  // Another patient today

        // Assign encounters to patients and care providers
        patient1.addEncounter(enc1);
        patient2.addEncounter(enc2);
        patient3.addEncounter(enc3);
        patient4.addEncounter(enc4);

        enc1.setCareProvider(cp1);
        enc2.setCareProvider(cp1);
        enc3.setCareProvider(cp2);
        enc4.setCareProvider(cp2);

        medicalEncounterRepository.save(enc1);
        medicalEncounterRepository.save(enc2);
        medicalEncounterRepository.save(enc3);
        medicalEncounterRepository.save(enc4);

        // Create HealthServices and link them
        // For Hypertension (Alice) - add a service on yesterday's encounter
        HealthService service1 = new HealthService("Prescribed beta blockers", "Medication");
        hi1.addHealthService(service1);
        enc1.addHealthService(service1);
        healthServiceRepository.save(service1);

        // For Diabetes (Alice) - add a service on yesterday's encounter
        HealthService service2 = new HealthService("Dietary guidelines provided", "Counseling");
        hi2.addHealthService(service2);
        enc1.addHealthService(service2);
        healthServiceRepository.save(service2);

        // For Asthma (Bob) - NO service added (for testing query for issues without a service)
        // For High Cholesterol (Charlie) - add a service on two days ago encounter via Dr. Jane Smith
        HealthService service3 = new HealthService("Prescribed statins", "Medication");
        hi4.addHealthService(service3);
        enc3.addHealthService(service3);
        healthServiceRepository.save(service3);

        // For Arthritis (Dana) - add a service on today's encounter via Dr. Jane Smith
        HealthService service4 = new HealthService("Physical therapy scheduled", "Therapy");
        hi5.addHealthService(service4);
        enc4.addHealthService(service4);
        healthServiceRepository.save(service4);
    }
}
