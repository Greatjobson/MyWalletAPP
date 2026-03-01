package com.finapp.finapp.Model.Entity;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "transaction")
@Data
public class Transaction {
    @Id
    private String id;
    @Indexed
    private String tagId;
    @Indexed
    private String assetId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Сумма должна быть больше нуля")
    @Digits(integer = 12, fraction = 2) // 12 знаков до запятой, 2 после (тиыны)
    private BigDecimal amount;

    @CreatedDate
    private LocalDate date;
    private String note;

}
