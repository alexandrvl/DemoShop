package com.example.demo.store.controller;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.common.RestDocsTest;
import com.example.demo.store.model.dto.ItemDto;
import com.example.demo.store.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestDocsTest(ItemController.class)
public class ItemControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ItemService itemService;

    @Test
    public void shouldGetItemsByGroupName() throws Exception {
        var response = new ItemDto()
                .setId(1L)
                .setItemGroupName("group_name")
                .setName("item name")
                .setAmount(BigDecimal.valueOf(10.00))
                .setCount(5);
        when(itemService.getAllByGroupName("group_name")).thenReturn(singletonList(response));
        this.mockMvc.perform(get("/api/v1/item/item-group/{group_name}", "group_name"))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("api/v1/item/item-group",
                        preprocessResponse(prettyPrint()),
                        pathParameters(parameterWithName("group_name").description("Group name for item"))));

    }

    @Test
    public void shouldCreateItem() throws Exception {
        var request = new ItemDto()
                .setItemGroupName("group_name")
                .setName("item name")
                .setAmount(BigDecimal.valueOf(10.00))
                .setCount(5);
        var response = request.toBuilder().version(1).id(1L).build();
        when(itemService.createItems(singletonList(request))).thenReturn(singletonList(response));
        this.mockMvc.perform(post("/api/v1/item/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(singletonList(request))))
                .andExpect(status().isCreated())
                .andDo(document("api/v1/item/create", preprocessResponse(prettyPrint())));
    }

}
