package com.example.demo.store.controller;

import static com.example.demo.common.rest.Api.API_URI_V1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.store.model.dto.ItemDto;
import com.example.demo.store.service.ItemService;

import lombok.RequiredArgsConstructor;

@RequestMapping(API_URI_V1 + "/item")
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ItemDto> create(@RequestBody @Validated List<ItemDto> items) {
        return itemService.createItems(items);
    }

    @GetMapping("/item-group/{groupName}")
    public List<ItemDto> getByGroupName(@PathVariable(value = "groupName") String groupName) {
        return itemService.getAllByGroupName(groupName);
    }

}
