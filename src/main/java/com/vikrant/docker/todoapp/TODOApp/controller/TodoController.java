package com.vikrant.docker.todoapp.TODOApp.controller;

import com.vikrant.docker.todoapp.TODOApp.model.Todo;
import com.vikrant.docker.todoapp.TODOApp.repository.TodoRepository;
import com.vikrant.docker.todoapp.TODOApp.view.TodoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping("/")
    public String index(Model model) {
        ArrayList<Todo> todos = (ArrayList<Todo>)todoRepository.findAll();
        model.addAttribute("newTodo" , new Todo());
        model.addAttribute("todos", new TodoViewModel(todos));
        return "index";
    }

    @RequestMapping("/add")
    public String addTodo(@ModelAttribute Todo addTodo) {
        Todo item = new Todo(addTodo.getCategory(), addTodo.getName());
        todoRepository.save(item);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateTodo(@ModelAttribute TodoViewModel requestItems) {
        for (Todo requestItem : requestItems.getTodoList() ) {
            Todo todo = new Todo(requestItem.getCategory(), requestItem.getName());
            todo.setStatus(requestItem.isStatus());
            todo.setId(requestItem.getId());
            todoRepository.save(todo);
        }
        return "redirect:/";
    }

}
