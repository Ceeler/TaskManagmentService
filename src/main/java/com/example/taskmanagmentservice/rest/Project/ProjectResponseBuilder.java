package com.example.taskmanagmentservice.rest.Project;

import com.example.taskmanagmentservice.database.structure.Project.Project;
import com.example.taskmanagmentservice.database.structure.Project.ProjectRepository;
import com.example.taskmanagmentservice.database.structure.Task.Task;
import com.example.taskmanagmentservice.database.structure.Task.TaskRepository;
import com.example.taskmanagmentservice.database.structure.User.User;
import com.example.taskmanagmentservice.database.structure.User.UserRepository;
import com.example.taskmanagmentservice.model.request.ProjectData;
import com.example.taskmanagmentservice.model.response.ProjectAdditionalInfo;
import com.example.taskmanagmentservice.model.response.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class ProjectResponseBuilder {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<ProjectInfo> processAddNewProject(ProjectData projectData){
        Project project = Project.builder()
                .name(projectData.getName())
                .description(projectData.getDescription())
                .build();
        project = projectRepository.save(project);

        return new ResponseEntity<>(new ProjectInfo(project), HttpStatus.OK);
    }

    public ResponseEntity<ProjectInfo> processGetProjectInfoById(Long id){

        Project project = projectRepository.findById(id).get();
        return new ResponseEntity<>(new ProjectInfo(project), HttpStatus.OK);

    }

    public ResponseEntity<Map<ProjectInfo, ProjectAdditionalInfo>> processGetProjectFullInfo(){

        Iterable<Task> tasks = taskRepository.findAll();
        Iterable<User> users = userRepository.findAll();

        Map<ProjectInfo, ProjectAdditionalInfo> ap = new TreeMap();

        tasks.forEach(task -> {
            if(task.getProjectId() != null){


            ProjectAdditionalInfo temp;
            ProjectInfo p = new ProjectInfo(task.getProjectId());

            if(ap.containsKey(p)) {
                temp = ap.get(p);
            }else{
                temp = new ProjectAdditionalInfo();
            }
            temp.addNewTask(task);
            ap.put(p,temp);
            }
        });

        users.forEach(user -> {
            if(user.getProject() != null) {
                ProjectAdditionalInfo temp;

                ProjectInfo p = new ProjectInfo(user.getProject());

                if (ap.containsKey(p)) {
                    temp = ap.get(p);
                } else {
                    temp = new ProjectAdditionalInfo();
                }
                temp.addNewUser(user);
                ap.put(p, temp);
            }
        });

        return new ResponseEntity<>(ap, HttpStatus.OK);
    }

}
