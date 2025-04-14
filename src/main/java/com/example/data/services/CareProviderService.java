package com.example.data.services;

import com.example.data.domain.CareProvider;
import java.util.List;

public interface CareProviderService {
    CareProvider save(CareProvider careProvider);
    List<CareProvider> findAll();
    CareProvider findById(Long id);
}
