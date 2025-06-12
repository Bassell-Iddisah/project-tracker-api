package com.gentleninja.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Developer {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @ElementCollection
    private List<String> skills = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "developer_task",joinColumns = @JoinColumn(name = "developer_id"),inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks = new HashSet<>();

    public Developer(Integer id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.email = mail;
    }
}

