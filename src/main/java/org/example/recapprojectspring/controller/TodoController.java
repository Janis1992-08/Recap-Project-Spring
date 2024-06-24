package org.example.recapprojectspring.controller;


import lombok.RequiredArgsConstructor;
import org.example.recapprojectspring.exceptions.TodoNotFoundException;
import org.example.recapprojectspring.model.Todo;
import org.example.recapprojectspring.model.TodoWithoutId;
import org.example.recapprojectspring.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody TodoWithoutId todoWithoutId) {
        todoService.addTodo(todoWithoutId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String id) throws TodoNotFoundException {
        Todo todo = todoService.getTodoById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable String id, @RequestBody TodoWithoutId todoWithoutId) throws TodoNotFoundException {
        todoService.updateTodo(id, todoWithoutId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String id) throws TodoNotFoundException {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
