package com.example.data.services;

import com.example.data.domain.MedicalEncounter;
import com.example.data.repositories.MedicalEncounterRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Primary
@Service
public class MedicalEncounterServiceImpl implements MedicalEncounterService {

    private final MedicalEncounterRepository encounterRepository;

    public MedicalEncounterServiceImpl(MedicalEncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    @Override
    public MedicalEncounter save(MedicalEncounter encounter) {
        return encounterRepository.save(encounter);
    }

    @Override
    public List<MedicalEncounter> findAll() {
        return (List<MedicalEncounter>) encounterRepository.findAll();
    }

    @Override
    public MedicalEncounter findById(Long id) {
        return encounterRepository.findById(id).orElse(null);
    }
}
