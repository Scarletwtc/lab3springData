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
@EqualsAndHashCode(exclude = {"encounters"})
public class CareProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialty;

    @OneToMany(mappedBy = "careProvider", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    //fetch lazy to load data only when needed so we don't waste time
    private Set<MedicalEncounter> encounters = new HashSet<>();

    public CareProvider(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    public void addEncounter(MedicalEncounter encounter) {
        this.encounters.add(encounter);
        encounter.setCareProvider(this);
    }
}
