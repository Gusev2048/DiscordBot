package com.BestBot.Repository;

import com.BestBot.Entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEntityRepository
        extends CrudRepository<ItemEntity, Long> {
}