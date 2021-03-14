package com.example.demo.order.controller;

import static com.example.demo.common.rest.Api.API_URI_V1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.order.model.dto.OrderItemDto;
import com.example.demo.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequestMapping(API_URI_V1 + "/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Validated List<OrderItemDto> items) {
        orderService.create(items);
    }

}
