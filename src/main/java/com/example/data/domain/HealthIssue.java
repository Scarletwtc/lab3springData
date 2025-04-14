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
@EqualsAndHashCode(exclude = {"patient", "healthServices"})
public class HealthIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @ManyToOne
    private Patient patient;

    @OneToMany(mappedBy = "healthIssue", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<HealthService> healthServices = new HashSet<>();

    public HealthIssue(String type) {
        this.type = type;
    }

    public void addHealthService(HealthService service) {
        healthServices.add(service);
        service.setHealthIssue(this);
    }
}
