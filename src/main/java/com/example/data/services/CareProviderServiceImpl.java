package com.example.data.services;

import com.example.data.domain.CareProvider;
import com.example.data.repositories.CareProviderRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Primary
@Service
public class CareProviderServiceImpl implements CareProviderService {

    private final CareProviderRepository careProviderRepository;

    public CareProviderServiceImpl(CareProviderRepository careProviderRepository) {
        this.careProviderRepository = careProviderRepository;
    }

    @Override
    public CareProvider save(CareProvider careProvider) {
        return careProviderRepository.save(careProvider);
    }

    @Override
    public List<CareProvider> findAll() {
        return (List<CareProvider>) careProviderRepository.findAll();
    }

    @Override
    public CareProvider findById(Long id) {
        return careProviderRepository.findById(id).orElse(null);
    }
}
