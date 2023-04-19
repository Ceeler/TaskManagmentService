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
public class ProjectInfo implements Comparable<ProjectInfo> {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectInfo that = (ProjectInfo) o;
        return id.equals(that.id);
    }

    @Override
    public int compareTo(ProjectInfo o) {
        return this.id.compareTo(o.id);
    }
}
