package com.example.data.services;

import com.example.data.domain.HealthIssue;
import java.util.List;

public interface HealthIssueService {
    HealthIssue save(HealthIssue healthIssue);
    List<HealthIssue> findAll();
    HealthIssue findById(Long id);
}
