package com.example.demo.store.repository;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import com.example.demo.store.model.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> getAllByItemGroupName(String groupName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "3000") })
    Item getAndLockOneById(Long id);

}
