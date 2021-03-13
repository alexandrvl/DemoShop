package com.example.demo.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.store.model.domain.ItemGroup;

public interface ItemGroupRepository extends JpaRepository<ItemGroup, Long> {

    Optional<ItemGroup> findOneByName(String name);

}
