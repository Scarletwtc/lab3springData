package com.example.data.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"healthIssues", "encounters"})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<HealthIssue> healthIssues = new HashSet<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<MedicalEncounter> encounters = new HashSet<>();

    public Patient(String name) {
        this.name = name;
    }

    public void addHealthIssue(HealthIssue issue) {
        healthIssues.add(issue);
        issue.setPatient(this);
    }

    public void addEncounter(MedicalEncounter encounter) {
        encounters.add(encounter);
        encounter.setPatient(this);
    }
}
