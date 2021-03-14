package com.example.demo.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.order.model.dto.OrderItemDto;
import com.example.demo.store.service.ItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final ItemService itemService;

    @Transactional
    public void create(List<OrderItemDto> items) {
        //just some dummy logic for demo
        items.forEach(item -> itemService.updateItemCount(item.getItemId(), item.getQty()));
    }

}
