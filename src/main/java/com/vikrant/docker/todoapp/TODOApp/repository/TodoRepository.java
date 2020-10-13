package com.vikrant.docker.todoapp.TODOApp.repository;

import org.springframework.data.repository.CrudRepository;
import com.vikrant.docker.todoapp.TODOApp.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long>{
}
