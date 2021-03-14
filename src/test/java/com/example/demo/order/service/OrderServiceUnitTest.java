package com.example.demo.order.service;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.order.model.dto.OrderItemDto;
import com.example.demo.store.service.ItemService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {

    @InjectMocks
    private OrderService testable;
    @Mock
    private ItemService itemService;

    @Test
    public void shouldPlaceOrder() {
        var itemId = 1L;
        var claimed = 1;
        var orderRequest = new OrderItemDto().setItemId(itemId).setQty(claimed);
        testable.create(singletonList(orderRequest));
        verify(itemService, atLeastOnce()).updateItemCount(itemId, claimed);
    }

}
