package com.example.demo.common.job;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
@ConstructorBinding
@ConfigurationProperties("demo.job")
@Validated
public class JobProperty {

    @NotNull
    @NotBlank
    private final String path;

}
