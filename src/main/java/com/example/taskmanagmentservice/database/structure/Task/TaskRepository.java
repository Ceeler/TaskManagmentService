package com.example.taskmanagmentservice.database.structure.Task;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    Optional<Task> findById(Long id);

    @EntityGraph(attributePaths = {"projectId"})
    Iterable<Task> findAll();
//    @Query(value = "SELECT t FROM Task t WHERE t.projectId.id = ?1 ")
//    Optional<List<Task>> findAllByProjectId(Long id);

}
