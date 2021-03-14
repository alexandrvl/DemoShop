package com.example.demo.store.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.error.DemoBusinessException;
import com.example.demo.store.mapper.ItemMapper;
import com.example.demo.store.model.dto.ItemDto;
import com.example.demo.store.repository.ItemGroupRepository;
import com.example.demo.store.repository.ItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemGroupRepository itemGroupRepository;
    private final ItemMapper mapper;

    @Transactional(readOnly = true)
    public List<ItemDto> getAllByGroupName(String groupName) {
        return itemRepository.getAllByItemGroupName(groupName)
                .stream()
                .map(mapper::from)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public ItemDto getById(Long id) {
        return mapper.from(itemRepository.getOne(id));
    }

    @Transactional
    public List<ItemDto> createItems(List<ItemDto> items) {
        return items.stream().map(this::createItem).collect(toList());
    }

    @Transactional
    public ItemDto createItem(ItemDto item) {
        var group = itemGroupRepository.findOneByName(item.getItemGroupName())
                .orElseThrow(() -> new DemoBusinessException(String.format("Wrong group name %s", item.getItemGroupName())));
        var itemEntity = mapper.to(item);
        itemEntity.setItemGroup(group);
        return mapper.from(itemRepository.save(itemEntity));
    }

    @Transactional
    public void updateItemCount(Long id, Integer count) {
        if (count <= 0) {
            throw new DemoBusinessException(String.format("Item count %d is wrong", count));
        }
        var item = itemRepository.getAndLockOneById(id);
        var newCount = item.getCount() - count;
        if (newCount < 0) {
            throw new DemoBusinessException(String.format("Can't claim current count %d, remaining is %d", count, item.getCount()));
        }
        item.setCount(newCount);
        itemRepository.saveAndFlush(item);
    }

}
