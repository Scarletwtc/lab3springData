package com.example.data.services;

import com.example.data.domain.MedicalEncounter;
import java.util.List;

public interface MedicalEncounterService {
    MedicalEncounter save(MedicalEncounter encounter);
    List<MedicalEncounter> findAll();
    MedicalEncounter findById(Long id);
}
