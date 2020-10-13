package com.vikrant.docker.todoapp.TODOApp.view;

import com.vikrant.docker.todoapp.TODOApp.model.Todo;

import javax.validation.Valid;
import java.util.ArrayList;

public class TodoViewModel {

    @Valid
    private ArrayList<Todo> todoList = new ArrayList<Todo>();

    public TodoViewModel(ArrayList<Todo> todoList) {
        this.todoList = todoList;
    }

    public ArrayList<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(ArrayList<Todo> todoList) {
        this.todoList = todoList;
    }
}
