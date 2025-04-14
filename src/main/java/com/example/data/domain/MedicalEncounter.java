package com.example.data.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"healthServices"})
public class MedicalEncounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private CareProvider careProvider;

    @OneToMany(mappedBy = "medicalEncounter", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<HealthService> healthServices = new HashSet<>();

    public MedicalEncounter(LocalDate date) {
        this.date = date;
    }

    public void addHealthService(HealthService service) {
        this.healthServices.add(service);
        service.setMedicalEncounter(this);
    }
}
