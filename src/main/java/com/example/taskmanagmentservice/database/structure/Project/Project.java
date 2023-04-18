package com.example.taskmanagmentservice.database.structure.Project;

import com.example.taskmanagmentservice.database.structure.User.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    //name - название проекта (string)
    private String name;

    //description - описание проекта (string)
    private String description;

    //created_at - дата создания проекта (datetime)
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    //updated_at - дата последнего обновления проекта (datetime)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

}
