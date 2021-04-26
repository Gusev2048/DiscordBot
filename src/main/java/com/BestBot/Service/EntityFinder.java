package com.BestBot.Service;

import com.BestBot.Entity.ItemEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class EntityFinder {

    private TestParser testParser;

    public EntityFinder(TestParser testParser) {
        this.testParser = testParser;
    }

    public Optional<ItemEntity> getItemEntity(String input){
        Map<String, ItemEntity> items = testParser.getItems();
        Set<Map.Entry<String, ItemEntity>> itemsEntry = items.entrySet();

        return Optional.of(itemsEntry.stream()
                .filter(e -> e.getKey().contains(input.toLowerCase())).findAny()
                .orElse(itemsEntry.stream().filter(e -> e.getKey()
                        .contains("Hurri".toLowerCase()))
                        .findAny()
                        .get())
                .getValue());
    }
}
