package com.example.data.services;

import com.example.data.domain.HealthIssue;
import com.example.data.repositories.HealthIssueRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Primary
@Service
public class HealthIssueServiceImpl implements HealthIssueService {

    private final HealthIssueRepository healthIssueRepository;

    public HealthIssueServiceImpl(HealthIssueRepository healthIssueRepository) {
        this.healthIssueRepository = healthIssueRepository;
    }

    @Override
    public HealthIssue save(HealthIssue healthIssue) {
        return healthIssueRepository.save(healthIssue);
    }

    @Override
    public List<HealthIssue> findAll() {
        return (List<HealthIssue>) healthIssueRepository.findAll();
    }

    @Override
    public HealthIssue findById(Long id) {
        return healthIssueRepository.findById(id).orElse(null);
    }
}
