package com.example.data.repositories;

import com.example.data.domain.Patient;
import com.example.data.domain.MedicalEncounter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface MedicalEncounterRepository extends CrudRepository<MedicalEncounter, Long> {

    @Query("SELECT DISTINCT me.patient FROM MedicalEncounter me WHERE me.date = :encounterDate")
    List<Patient> findPatientsByEncounterDate(@Param("encounterDate") LocalDate encounterDate);

    // Retrieve distinct patients for a given care provider name.
    @Query("SELECT DISTINCT me.patient FROM MedicalEncounter me WHERE me.careProvider.name = :providerName")
    List<Patient> findPatientsByCareProviderName(@Param("providerName") String providerName);
}
