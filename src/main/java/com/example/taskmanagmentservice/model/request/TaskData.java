package com.example.taskmanagmentservice.model.request;


import com.example.taskmanagmentservice.database.structure.Project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskData {

    private String title;

    private String description;

    private Optional<Long> project_id;

}
