package org.example.recapprojectspring.service;

import org.example.recapprojectspring.exceptions.TodoNotFoundException;
import org.example.recapprojectspring.model.Status;
import org.example.recapprojectspring.model.Todo;
import org.example.recapprojectspring.model.TodoWithoutId;
import org.example.recapprojectspring.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private IdService idService;

    @InjectMocks
    private TodoService todoService;

    private TodoWithoutId todoWithoutId;
    private Todo todo;

    @BeforeEach
    void setUp() {
        todoWithoutId = new TodoWithoutId("Test Description", Status.OPEN);
        todo = new Todo("1", "Test Description", Status.OPEN);
    }

    @Test
    void testGetAllTodos() {
        List<Todo> todos = new ArrayList<>();
        todos.add(todo);

        when(todoRepository.findAll()).thenReturn(todos);

        List<Todo> result = todoService.getAllTodos();
        assertEquals(1, result.size());
        assertEquals("Test Description", result.get(0).description());

        verify(todoRepository, times(1)).findAll();
    }

    @Test
    void testAddTodo() {
        when(idService.idGetter()).thenReturn("1");
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        todoService.addTodo(todoWithoutId);

        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void testGetTodoById() throws TodoNotFoundException {
        when(todoRepository.findById("1")).thenReturn(Optional.of(todo));

        Todo result = todoService.getTodoById("1");
        assertNotNull(result);
        assertEquals("Test Description", result.description());

        verify(todoRepository, times(1)).findById("1");
    }

    @Test
    void testGetTodoById_NotFound() {
        when(todoRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(TodoNotFoundException.class, () -> todoService.getTodoById("1"));

        verify(todoRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateTodo() throws TodoNotFoundException {
        when(todoRepository.findById("1")).thenReturn(Optional.of(todo));

        TodoWithoutId updatedTodoWithoutId = new TodoWithoutId("Updated Description", Status.IN_PROGRESS);
        todoService.updateTodo("1", updatedTodoWithoutId);

        verify(todoRepository, times(1)).findById("1");
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void testDeleteTodoById() throws TodoNotFoundException {
        when(todoRepository.findById("1")).thenReturn(Optional.of(todo));

        todoService.deleteTodoById("1");

        verify(todoRepository, times(1)).findById("1");
        verify(todoRepository, times(1)).delete(todo);
    }
}
