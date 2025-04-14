package com.example.data.services;

import com.example.data.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class HealthcareQueryServiceImpl implements HealthcareQueryService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<HealthIssue> getHealthIssuesByPatientId(Long patientId) {
        String jpql = "SELECT hi FROM HealthIssue hi WHERE hi.patient.id = :patientId";
        TypedQuery<HealthIssue> query = em.createQuery(jpql, HealthIssue.class);
        query.setParameter("patientId", patientId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<HealthIssue> getHealthIssuesWithServiceByPatientId(Long patientId) {
        String jpql = "SELECT hi FROM HealthIssue hi WHERE hi.patient.id = :patientId AND SIZE(hi.healthServices) > 0";
        TypedQuery<HealthIssue> query = em.createQuery(jpql, HealthIssue.class);
        query.setParameter("patientId", patientId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Patient> getPatientsByEncounterDate(LocalDate date) {
        String jpql = "SELECT DISTINCT me.patient FROM MedicalEncounter me WHERE me.date = :encounterDate";
        TypedQuery<Patient> query = em.createQuery(jpql, Patient.class);
        query.setParameter("encounterDate", date);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Patient> getPatientsByCareProviderName(String providerName) {
        String jpql = "SELECT DISTINCT me.patient FROM MedicalEncounter me WHERE me.careProvider.name = :providerName";
        TypedQuery<Patient> query = em.createQuery(jpql, Patient.class);
        query.setParameter("providerName", providerName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<CareProvider> getCareProvidersByHealthIssueType(String issueType) {
        String jpql = "SELECT DISTINCT me.careProvider FROM HealthService hs JOIN hs.medicalEncounter me WHERE hs.healthIssue.type = :issueType";
        TypedQuery<CareProvider> query = em.createQuery(jpql, CareProvider.class);
        query.setParameter("issueType", issueType);
        return query.getResultList();
    }
}
