package com.odc.todolist.repository;


import com.odc.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepoository extends JpaRepository<Task, Integer> {



}
