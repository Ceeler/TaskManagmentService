package com.example.taskmanagmentservice.model.response;


import com.example.taskmanagmentservice.database.enums.Role;
import com.example.taskmanagmentservice.database.enums.Status;
import com.example.taskmanagmentservice.database.structure.Task.Task;
import com.example.taskmanagmentservice.database.structure.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAdditionalInfo {

    public void addNewTask(Task task){

        taskList.add(new TaskShortsInfo(task));

    }

    public void addNewUser(User user){

        userList.add(new UserShortsInfo(user));

    }

    List<UserShortsInfo> userList = new ArrayList<>();

    List<TaskShortsInfo> taskList = new ArrayList<>();

}
@Data
class UserShortsInfo{

    public UserShortsInfo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

    private Long id;

    private String username;

    private Role role;

}
@Data
class TaskShortsInfo{

    public TaskShortsInfo(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.createdAt = task.getCreatedAt();
    }

    private Long id;

    private String title;

    private String description;

    private Status status;

    private Instant createdAt;


}
