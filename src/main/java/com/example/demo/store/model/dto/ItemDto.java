package com.example.demo.store.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ItemDto {

    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String name;

    @NotNull
    @PositiveOrZero
    private Integer count;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 10, fraction = 2)
    private BigDecimal amount;

    @NotNull
    @NotBlank
    private String itemGroupName;

}
