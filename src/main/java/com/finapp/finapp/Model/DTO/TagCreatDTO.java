package com.finapp.finapp.Model.DTO;

import com.finapp.finapp.Model.Entity.Transaction;
import com.finapp.finapp.Model.TransactionType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TagCreatDTO {

    @NotBlank
    private String userId;

    @Size(min = 2, max = 50)
    @NotBlank
    private String name;

    @NotNull
    private TransactionType transactionType;
}
