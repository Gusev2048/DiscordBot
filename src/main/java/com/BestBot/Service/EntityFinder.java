package com.BestBot.Service;

import com.BestBot.Entity.ItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EntityFinder {

    private TestParser testParser;

    public EntityFinder(TestParser testParser) {
        this.testParser = testParser;
    }

    public Optional<ItemEntity> getItemEntity(String itemNamePart){
        Map<String, ItemEntity> items = testParser.getItems();
        Set<Map.Entry<String, ItemEntity>> itemsEntry = items.entrySet();

        return Optional.of(itemsEntry.stream()
                .filter(e -> e.getKey().contains(itemNamePart.toLowerCase())).findAny()
                .orElse(itemsEntry.stream().filter(e -> e.getKey()
                        .contains("Hurri".toLowerCase()))
                        .findAny()
                        .get())
                .getValue());
    }
    public Optional<List<ItemEntity>> getCategoryItems(String inputCategoryName) {
        List<ItemEntity> itemEntityList = testParser.getItems().values().stream()
                .toList();

        return Optional.of(itemEntityList.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(itemEntityList.stream()
                        .map(r -> r.getCategory())
                        .collect(Collectors.toSet()).stream()
                        .filter(r -> r.toLowerCase().contains(inputCategoryName))
                        .findFirst()
                        .orElse("weapons")))
                .toList());
    }
}
