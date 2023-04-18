package com.example.taskmanagmentservice.database.structure.Task;

import com.example.taskmanagmentservice.database.enums.Status;
import com.example.taskmanagmentservice.database.structure.Project.Project;
import com.example.taskmanagmentservice.database.structure.User.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;


@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    //title - заголовок задачи (string)
    private String title;

    //description - описание задачи (string)
    private String description;

    //status - статус задачи (enum - NEW, IN_PROGRESS, DONE)
    @Enumerated(EnumType.STRING)
    private Status status;

    //created_at - дата создания задачи (datetime)
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    //updated_at - дата последнего обновления задачи (datetime)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    //user_id - идентификатор пользователя, который создал задачу (long)
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Nonnull
    private User userId;

    //project_id - идентификатор проекта, которому назначена задача (long, может быть null)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project projectId;
}
