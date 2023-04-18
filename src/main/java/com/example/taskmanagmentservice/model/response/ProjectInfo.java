package com.example.taskmanagmentservice.model.response;

import com.example.taskmanagmentservice.database.structure.Project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfo {

    public ProjectInfo(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.createdAt = project.getCreatedAt();
    }

    private Long id;

    private String name;

    private String description;

    private Instant createdAt;

}
