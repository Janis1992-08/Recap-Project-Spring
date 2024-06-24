package org.example.recapprojectspring.service;

import lombok.RequiredArgsConstructor;
import org.example.recapprojectspring.exceptions.TodoNotFoundException;
import org.example.recapprojectspring.model.Status;
import org.example.recapprojectspring.model.Todo;
import org.example.recapprojectspring.model.TodoWithoutId;
import org.example.recapprojectspring.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class TodoService {

    private final TodoRepository todoRepository;
    public final IdService idService;


    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public void addTodo(TodoWithoutId todoWithoutId) {
        Todo newTodo = new Todo(idService.idGetter(), todoWithoutId.description(), Status.OPEN);
        todoRepository.save(newTodo);
    }


    public Todo getTodoById(String id) throws TodoNotFoundException {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElseThrow(() -> new TodoNotFoundException("Todo not found"));
    }


    public void updateTodo(String id, TodoWithoutId todoWithoutId) throws TodoNotFoundException {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));

        Status newStatus = Status.valueOf(todoWithoutId.status().name());
        Todo updatedTodo = existingTodo
                .withDescription(todoWithoutId.description())
                .withStatus(newStatus);
        todoRepository.save(updatedTodo);
    }


    public void deleteTodoById(String id) throws TodoNotFoundException {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Todo not found"));
        todoRepository.delete(todo);
    }




}
