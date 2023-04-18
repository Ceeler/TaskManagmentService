package com.example.taskmanagmentservice.database.structure.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//TODO extends JPARepository difference
public interface UserRepository extends CrudRepository <User, Long> {

    Optional<User> findByUsername(String username);


}
