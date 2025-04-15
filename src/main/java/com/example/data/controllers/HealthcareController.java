package com.example.data.controllers;

import com.example.data.domain.CareProvider;
import com.example.data.domain.HealthIssue;
import com.example.data.domain.HealthService;
import com.example.data.domain.Patient;
import com.example.data.services.CareProviderService;
import com.example.data.services.HealthIssueService;
import com.example.data.services.HealthServiceService;
import com.example.data.services.HealthcareQueryService;
import com.example.data.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HealthcareController {

    private final HealthcareQueryService queryService;
    private final PatientService patientService;
    private final CareProviderService careProviderService;
    private final HealthIssueService healthIssueService;
    private final HealthServiceService healthServiceService;

    public HealthcareController(HealthcareQueryService queryService,
                                PatientService patientService,
                                CareProviderService careProviderService,
                                HealthIssueService healthIssueService,
                                HealthServiceService healthServiceService) {
        this.queryService = queryService;
        this.patientService = patientService;
        this.careProviderService = careProviderService;
        this.healthIssueService = healthIssueService;
        this.healthServiceService = healthServiceService;
    }


    // Index page with links
    @GetMapping("/")
    public String index() {
        return "index"; // See index.html below.
    }

    // List all patients
    @GetMapping("/patients")
    public String listPatients(Model model) {
        List<Patient> patients = patientService.findAll();
        model.addAttribute("patients", patients);
        return "patients";
    }

    // List all care providers
    @GetMapping("/careproviders")
    public String listCareProviders(Model model) {
        List<CareProvider> providers = careProviderService.findAll();
        model.addAttribute("providers", providers);
        return "careproviders";
    }

    // List all health issues
    @GetMapping("/healthissues")
    public String listHealthIssues(Model model) {
        List<HealthIssue> issues = healthIssueService.findAll();
        model.addAttribute("issues", issues);
        return "healthissues";
    }

    // List all health services
    @GetMapping("/healthservices")
    public String listHealthServices(Model model) {
        List<HealthService> services = healthServiceService.findAll();
        model.addAttribute("services", services);
        return "healthservices";     }



    // Display a page that has forms for running the queries
    @GetMapping("/queries")
    public String queriesPage() {
        return "queries";
    }

    // Query 1: Given patient ID, display both all health issues and those with at least one service.
    @GetMapping("/query/patientIssues")
    public String getPatientIssues(@RequestParam("patientId") Long patientId, Model model) {
        List<HealthIssue> allHealthIssues = queryService.getHealthIssuesByPatientId(patientId);
        List<HealthIssue> issuesWithServices = queryService.getHealthIssuesWithServiceByPatientId(patientId);
        model.addAttribute("patientId", patientId);
        model.addAttribute("allHealthIssues", allHealthIssues);
        model.addAttribute("issuesWithServices", issuesWithServices);
        return "patient-healthissues"; // See patient-healthissues.html below.
    }

    // Query 2: Given a date, display all patients who had an encounter on that date.
    @GetMapping("/query/encounters")
    public String getPatientsByEncounterDate(@RequestParam("date") String dateStr, Model model) {
        LocalDate date = LocalDate.parse(dateStr);
        List<Patient> patients = queryService.getPatientsByEncounterDate(date);
        model.addAttribute("encounterDate", date);
        model.addAttribute("patients", patients);
        return "encounters"; // See encounters.html below.
    }

    // Query 3: Given a CareProvider name, display all his/her patients.
    @GetMapping("/query/careproviderPatients")
    public String getPatientsByCareProvider(@RequestParam("providerName") String providerName, Model model) {
        List<Patient> patients = queryService.getPatientsByCareProviderName(providerName);
        model.addAttribute("providerName", providerName);
        model.addAttribute("patients", patients);
        return "careprovider-patients"; // See careprovider-patients.html below.
    }

    @GetMapping("/query/healthissueCareproviders")
    public String getCareProvidersByHealthIssue(@RequestParam("issueType") String issueType, Model model) {
        List<CareProvider> providers = queryService.getCareProvidersByHealthIssueType(issueType);
        model.addAttribute("issueType", issueType);
        model.addAttribute("providers", providers);
        return "healthissue-careproviders";
    }

}
