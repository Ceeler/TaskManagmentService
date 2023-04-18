package com.example.taskmanagmentservice.rest.Project;

import com.example.taskmanagmentservice.database.structure.Project.Project;
import com.example.taskmanagmentservice.database.structure.Project.ProjectRepository;
import com.example.taskmanagmentservice.model.request.ProjectData;
import com.example.taskmanagmentservice.model.response.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectResponseBuilder {

    @Autowired
    ProjectRepository projectRepository;

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

}
