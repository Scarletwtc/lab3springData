package com.example.data.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"healthIssue", "medicalEncounter"})
public class HealthService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String type;

    @ManyToOne
    private HealthIssue healthIssue;

    @ManyToOne
    private MedicalEncounter medicalEncounter;

    public HealthService(String description, String type) {
        this.description = description;
        this.type = type;
    }
}
