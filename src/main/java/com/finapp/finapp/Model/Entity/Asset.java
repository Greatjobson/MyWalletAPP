package com.finapp.finapp.Model.Entity;

import com.finapp.finapp.Model.Currency;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "assets")
public class Asset {
    @Id
    private String id;

    private String userId;
    private Currency currency;
    private BigDecimal amount;
    private String note;

}
