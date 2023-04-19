package com.example.taskmanagmentservice.rest.Task;


import com.example.taskmanagmentservice.model.request.ProjectIdRequest;
import com.example.taskmanagmentservice.model.request.TaskData;
import com.example.taskmanagmentservice.model.request.NewStatusRequest;
import com.example.taskmanagmentservice.model.response.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task/")
public class TaskController {

    @Autowired
    TaskResponseBuilder taskResponseBuilder;

    @PostMapping(path = "/add")
    public ResponseEntity<TaskInfo> addNewTask(
            @RequestBody TaskData taskData
    ){
        return taskResponseBuilder.processAddNewTask(taskData);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskInfo> getTaskInfoById(
            @PathVariable Long id
    ){
    return taskResponseBuilder.processGetTaskInfoById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskInfo> updateTaskStatusById(
            @PathVariable Long id,
            @RequestBody NewStatusRequest status
    ){
        return taskResponseBuilder.processUpdateTaskStatusById(id, status.getStatus());
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<TaskInfo>> getAllTasks(){
        return taskResponseBuilder.processGetAllTasks();
    }

    @PutMapping(path = "/{id}/setProject")
    public ResponseEntity<TaskInfo> setProjectToTask(
            @PathVariable Long taskId,
            @RequestBody ProjectIdRequest projectId
    ){
        return taskResponseBuilder.processSetProjectToTask(taskId, projectId.getProjectId());
    }

}
