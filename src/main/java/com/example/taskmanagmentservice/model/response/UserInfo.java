package com.example.taskmanagmentservice.model.response;

import com.example.taskmanagmentservice.database.enums.Role;
import com.example.taskmanagmentservice.database.structure.Project.Project;
import com.example.taskmanagmentservice.database.structure.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.spi.LocaleNameProvider;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    public UserInfo(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.project = user.getProject();
    }

    private Long id;

    private String username;

    private Role role;

    private Project project;

}
