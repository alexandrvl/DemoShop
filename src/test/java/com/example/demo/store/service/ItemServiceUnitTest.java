package com.example.demo.store.service;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.common.error.DemoBusinessException;
import com.example.demo.store.mapper.ItemMapper;
import com.example.demo.store.model.domain.Item;
import com.example.demo.store.model.domain.ItemGroup;
import com.example.demo.store.model.dto.ItemDto;
import com.example.demo.store.repository.ItemGroupRepository;
import com.example.demo.store.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceUnitTest {

    @InjectMocks
    private ItemService testable;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ItemGroupRepository itemGroupRepository;
    @Spy
    private ItemMapper loanBalanceMapper = Mappers.getMapper(ItemMapper.class);

    @Test
    public void shouldReturnItemsByGroupName() {
        var groupName = "test group";
        var itemGroup = new ItemGroup().setName(groupName).setId(1L);
        var item = new Item().setItemGroup(itemGroup).setId(1L);
        when(itemRepository.getAllByItemGroupName(groupName))
                .thenReturn(singletonList(item));
        var result = testable.getAllByGroupName(groupName);
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.get(0).getItemGroupName()).isEqualTo(groupName);
    }

    @Test
    public void shouldGetById() {
        var itemId = 1L;
        var item = new Item().setId(itemId);
        when(itemRepository.getOne(itemId)).thenReturn(item);
        var result = testable.getById(itemId);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(itemId);
    }

    @Test
    public void shouldUpdateItemCount() {
        var itemId = 1L;
        var count = 3;
        var itemCount = 4;
        var item = new Item().setId(itemId).setCount(itemCount);
        when(itemRepository.getAndLockOneById(itemId)).thenReturn(item);
        testable.updateItemCount(itemId, count);
        assertThat(item.getCount()).isEqualTo(itemCount - count);
    }

    @Test
    public void shouldThrowExceptionWhenCountIsLesOrEqZero() {
        var itemId = 1L;
        var count = 0;
        var errorMessage = "Item count 0 is wrong";
        Throwable exception = assertThrows(DemoBusinessException.class, () -> testable.updateItemCount(itemId, count));
        MatcherAssert.assertThat(exception.getMessage(), is(errorMessage));
    }

    @Test
    public void shouldThrowExceptionWhenRemainingCountIsLesThanZero() {
        var itemId = 1L;
        var count = 5;
        var itemCount = 0;
        var errorMessage = "Can't claim current count 5, remaining is 0";
        var item = new Item().setId(itemId).setCount(itemCount);
        when(itemRepository.getAndLockOneById(itemId)).thenReturn(item);
        Throwable exception = assertThrows(DemoBusinessException.class, () -> testable.updateItemCount(itemId, count));
        MatcherAssert.assertThat(exception.getMessage(), is(errorMessage));
    }

    @Test
    public void shouldCreateItem() {
        var itemGroupName = "item group";
        var itemId = 1L;
        var itemName = "item name";
        var itemDto = new ItemDto().setId(itemId).setName(itemName).setItemGroupName(itemGroupName);
        var itemGroup = new ItemGroup().setName(itemGroupName);
        var itemEntity = new Item().setId(1L).setName(itemName).setItemGroup(itemGroup);
        when(itemGroupRepository.findOneByName(itemGroupName)).thenReturn(Optional.of(itemGroup));
        when(itemRepository.save(itemEntity)).thenReturn(itemEntity);
        var result = testable.createItem(itemDto);
        assertThat(result.getItemGroupName()).isEqualTo(itemGroupName);
        assertThat(result.getId()).isEqualTo(itemId);
        assertThat(result.getName()).isEqualTo(itemName);
    }

    @Test
    public void shouldCreateItems() {
        var itemGroupName = "item group";
        var itemId = 1L;
        var itemName = "item name";
        var itemDto = new ItemDto().setId(itemId).setName(itemName).setItemGroupName(itemGroupName);
        var itemGroup = new ItemGroup().setName(itemGroupName);
        var itemEntity = new Item().setId(1L).setName(itemName).setItemGroup(itemGroup);
        when(itemGroupRepository.findOneByName(itemGroupName)).thenReturn(Optional.of(itemGroup));
        when(itemRepository.save(itemEntity)).thenReturn(itemEntity);
        var result = testable.createItems(singletonList(itemDto));
        assertThat(result.get(0).getItemGroupName()).isEqualTo(itemGroupName);
        assertThat(result.get(0).getId()).isEqualTo(itemId);
        assertThat(result.get(0).getName()).isEqualTo(itemName);
    }

}
