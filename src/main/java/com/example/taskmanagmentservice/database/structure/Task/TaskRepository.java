package com.example.taskmanagmentservice.database.structure.Task;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    Optional<Task> findById(Long id);

}
