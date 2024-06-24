package org.example.recapprojectspring.model;


import lombok.With;
import org.springframework.data.annotation.Id;

@With
public record Todo(
        @Id
        String id,
        String description,
        Status status) {
}



