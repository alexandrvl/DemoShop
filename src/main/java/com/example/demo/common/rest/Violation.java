package com.example.demo.common.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Violation {

    private String fieldName;
    private String message;

}
