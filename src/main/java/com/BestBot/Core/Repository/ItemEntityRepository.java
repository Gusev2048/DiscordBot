package com.BestBot.Core.Repository;

import com.BestBot.Core.Entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEntityRepository
        extends CrudRepository<ItemEntity, Long> {
}