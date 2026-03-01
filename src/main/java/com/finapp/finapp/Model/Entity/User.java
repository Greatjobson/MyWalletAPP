package com.finapp.finapp.Model.Entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String _id;

    @Indexed
    private String name;

    @CreatedDate
    private LocalDate createdAt;
}
