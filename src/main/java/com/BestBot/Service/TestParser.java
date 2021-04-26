package com.BestBot.Service;

import com.BestBot.Entity.ItemEntity;
import com.BestBot.Repository.ItemEntityRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableAutoConfiguration
@PropertySource("classpath:token.properties")
public class TestParser implements CrossoutdbParser{

    private final String api;
    private final String botType;
    private ItemEntityRepository itemEntityRepository;
    private Map<String, ItemEntity> items;

    public TestParser(@Value("${getItemApi}") String api, @Value("${botType}") String botType, ItemEntityRepository itemEntityRepositoryNew) {
        this.api = api;
        this.itemEntityRepository = itemEntityRepositoryNew;
        this.botType = botType;
        this.items = new HashMap<>();
    }

    @Override
    public String getApi() {
        return api;
    }

    private String readFromApi() {

        byte[] chars;
        try(InputStream inputStream = new URL(api).openStream()){
            chars = inputStream.readAllBytes();
        } catch (IOException e ){
            return e.getMessage();
        }
        return new String(chars, StandardCharsets.UTF_8);
    }

    @Bean
    @Scheduled(fixedDelay = 300000)
    public void testTest(){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeAll;

        List<ItemEntity> itemList = new ArrayList<>();

        try{
            jsonNodeAll = objectMapper.readTree(readFromApi());
            Flux.fromIterable(jsonNodeAll)
                    .map(jsonNode -> {
                                ItemEntity e = new ItemEntity();
                                e.setId(jsonNode.get("id").asLong());
                                e.setName(jsonNode.get("name").asText());
                                e.setCategory(jsonNode.get("categoryName").asText());
                                e.setSellPrice(jsonNode.get("sellPrice").asDouble());
                                e.setBuyPrice(jsonNode.get("buyPrice").asDouble());
                                e.setSellOffers(jsonNode.get("sellOffers").asInt());
                                e.setBuyOrders(jsonNode.get("buyOrders").asInt());
                                e.setLastUpdateTime(jsonNode.get("timestamp").asText());
                                return e;
                            }
                    )
                    .subscribe(itemList::add);
        }catch (Exception e){
            e.printStackTrace();
        }

        itemList.stream()
                .filter(Objects::nonNull)
                .forEach(e -> itemEntityRepository.save(e));

        Flux.fromIterable(itemList)
                             .subscribe(itemEntity -> items.put(itemEntity.getId().toString(), itemEntity));
    }

    public Map<String, ItemEntity> getItems(){
        return this.items;
    }
}
