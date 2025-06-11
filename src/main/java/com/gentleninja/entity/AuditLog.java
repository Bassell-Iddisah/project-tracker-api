package com.gentleninja.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String actionType;

    @NotNull
    private String entityType;

    @NotNull
    private Integer entityId;

    @NotBlank
    private Timestamp timeStamp;

    @NotNull
    private String actorName;

    @NotBlank
    private Payload payload;
}
