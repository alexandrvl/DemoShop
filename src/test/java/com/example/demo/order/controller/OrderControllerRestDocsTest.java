package com.example.demo.order.controller;

import static java.util.Collections.singletonList;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.common.RestDocsTest;
import com.example.demo.order.model.dto.OrderItemDto;
import com.example.demo.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestDocsTest(OrderController.class)
public class OrderControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderService orderService;

    @Test
    public void shouldPlaceOrder() throws Exception {
        var itemId = 1L;
        var qnty = 1;
        var request = new OrderItemDto().setItemId(itemId).setQty(qnty);

        this.mockMvc.perform(post("/api/v1/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(singletonList(request))))
                .andExpect(status().isCreated())
                .andDo(document("api/v1/order/create", preprocessResponse(prettyPrint())));
    }

}
