package com.example.data.services;

import com.example.data.domain.HealthService;
import java.util.List;

public interface HealthServiceService {
    HealthService save(HealthService service);
    List<HealthService> findAll();
    HealthService findById(Long id);
}
