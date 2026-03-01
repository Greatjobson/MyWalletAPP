package com.finapp.finapp.Model.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionCreateDTO {

    @NotBlank
    private String tagId;

    @NotBlank
    private String assetId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Сумма должна быть больше нуля")
    @Digits(integer = 12, fraction = 2) // 12 знаков до запятой, 2 после (тиыны)
    private BigDecimal amount;

    @Size(min = 2, max = 50)
    private String note;
}
