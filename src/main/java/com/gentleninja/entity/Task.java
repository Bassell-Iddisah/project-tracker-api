package com.gentleninja.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    private String title;

    @Column(length = 1000)
    private String description;

//    @Enumerated(EnumType.STRING)
    @NotNull
    private String status;

    @NotNull
    private String dueDate;

    @ManyToMany(mappedBy = "tasks")
    @JsonBackReference
    private Set<Developer> developers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
