package com.finapp.finapp.Model.Entity;

import com.finapp.finapp.Model.TransactionType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags")
@Data
public class Tag {

    @Id
    private String id;

    @Indexed
    private String userId;
    private String name;
    private TransactionType transactionType;

}
