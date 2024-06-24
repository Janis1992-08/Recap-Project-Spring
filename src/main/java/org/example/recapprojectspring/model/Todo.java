package org.example.recapprojectspring.model;


import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@With
//@Document(collection = "todo")
public record Todo(
        @Id
        String id,
        String description,
        Status status) {
}



