package com.example.demo.common.error;

import lombok.Getter;

@Getter
public class DemoBusinessException extends RuntimeException {

    public DemoBusinessException(String message) {
        super(message);
    }

}
