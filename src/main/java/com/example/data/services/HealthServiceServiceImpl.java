package com.example.data.services;

import com.example.data.domain.HealthService;
import com.example.data.repositories.HealthServiceRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Primary
@Service
public class HealthServiceServiceImpl implements HealthServiceService {

    private final HealthServiceRepository healthServiceRepository;

    public HealthServiceServiceImpl(HealthServiceRepository healthServiceRepository) {
        this.healthServiceRepository = healthServiceRepository;
    }

    @Override
    public HealthService save(HealthService service) {
        return healthServiceRepository.save(service);
    }

    @Override
    public List<HealthService> findAll() {
        return (List<HealthService>) healthServiceRepository.findAll();
    }

    @Override
    public HealthService findById(Long id) {
        return healthServiceRepository.findById(id).orElse(null);
    }
}
