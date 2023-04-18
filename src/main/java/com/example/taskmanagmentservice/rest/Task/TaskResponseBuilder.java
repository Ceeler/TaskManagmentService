package com.example.taskmanagmentservice.rest.Task;


import com.example.taskmanagmentservice.database.enums.Status;
import com.example.taskmanagmentservice.database.structure.Task.Task;
import com.example.taskmanagmentservice.database.structure.Task.TaskRepository;
import com.example.taskmanagmentservice.database.structure.User.User;
import com.example.taskmanagmentservice.database.structure.User.UserRepository;
import com.example.taskmanagmentservice.model.request.TaskData;
import com.example.taskmanagmentservice.model.response.TaskInfo;
import com.example.taskmanagmentservice.model.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskResponseBuilder {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    //TODO Понять как поставить пользователя без БД
    public ResponseEntity<TaskInfo> processAddNewTask(TaskData taskData){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = Task.builder()
                .title(taskData.getTitle())
                .description(taskData.getDescription())
                .status(Status.NEW)
                .userId(userRepository.findById(user.getId()).get())
                .projectId(null)
                .build();
        task = taskRepository.save(task);
        TaskInfo taskInfo = TaskInfo.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .user(new UserInfo(task.getUserId()))
                .project(task.getProjectId())
                .build();
        return ResponseEntity.ok(taskInfo);
    }

    public ResponseEntity<TaskInfo> processGetTaskInfoById(Long id){

        Task t = taskRepository.findById(id).get();
        TaskInfo taskInfo = TaskInfo.builder()
                .project(t.getProjectId())
                .id(t.getId())
                .createdAt(t.getCreatedAt())
                .status(t.getStatus())
                .description(t.getDescription())
                .title(t.getTitle())
                .user(new UserInfo(t.getUserId()))
                .build();

        return new ResponseEntity<>(taskInfo, HttpStatus.OK);
    }


    //TODO почему 3 запроса???
    public ResponseEntity<TaskInfo> processUpdateTaskStatusById(Long id, String status){
        Status s;
        status = status.toUpperCase().strip();

        try {
            s = Status.valueOf(status);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Task t = taskRepository.findById(id).get();
        t.setStatus(s);
        t = taskRepository.save(t);

        TaskInfo taskInfo = TaskInfo.builder()
                .project(t.getProjectId())
                .id(t.getId())
                .createdAt(t.getCreatedAt())
                .status(t.getStatus())
                .description(t.getDescription())
                .title(t.getTitle())
                .user(new UserInfo(t.getUserId()))
                .build();

        return new ResponseEntity<>(taskInfo, HttpStatus.OK);
    }

    public ResponseEntity<List<TaskInfo>> processGetAllTasks(){

        List<TaskInfo> request = new ArrayList<>();
        Iterable<Task> tasks = taskRepository.findAll();
        tasks.forEach(
                task -> request.add(new TaskInfo(task))
        );

        return new ResponseEntity<>(request, HttpStatus.OK);

    }

}
