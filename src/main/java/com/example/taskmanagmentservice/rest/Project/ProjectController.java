package com.example.taskmanagmentservice.rest.Project;

import com.example.taskmanagmentservice.model.request.ProjectData;
import com.example.taskmanagmentservice.model.response.ProjectAdditionalInfo;
import com.example.taskmanagmentservice.model.response.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/project")
public class ProjectController {

    @Autowired
    ProjectResponseBuilder projectResponseBuilder;

    @PostMapping("/add")
    public ResponseEntity<ProjectInfo> addNewProject(
            @RequestBody ProjectData projectData
            ){
        return projectResponseBuilder.processAddNewProject(projectData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectInfo> getProjectInfoById(
            @PathVariable Long id
    ){
        return projectResponseBuilder.processGetProjectInfoById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<ProjectInfo, ProjectAdditionalInfo>> getProjectsFullInfo(){

        return projectResponseBuilder.processGetProjectFullInfo();
    }

}
