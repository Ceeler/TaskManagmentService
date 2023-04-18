package com.example.taskmanagmentservice.model.response;

import com.example.taskmanagmentservice.database.enums.Status;
import com.example.taskmanagmentservice.database.structure.Project.Project;
import com.example.taskmanagmentservice.database.structure.Task.Task;
import com.example.taskmanagmentservice.database.structure.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskInfo {

    public TaskInfo(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.createdAt = task.getCreatedAt();
        this.user = new UserInfo(task.getUserId());
        this.project = task.getProjectId();
    }


    private Long id;

    private String title;

    private String description;

    private Status status;

    private Instant createdAt;

    private UserInfo user;

    private Project project;

}
