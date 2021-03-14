package com.example.demo.store.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.demo.store.model.domain.Item;
import com.example.demo.store.model.dto.ItemDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ItemMapper {

    @Mapping(target = "itemGroupName", source = "itemGroup.name")
    ItemDto from(Item item);

    @Mapping(target = "itemGroup", ignore = true)
    Item to(ItemDto to);

}
