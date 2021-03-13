package com.example.demo.store.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemGroupDto {

    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String name;

}


