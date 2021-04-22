package com.BestBot.Core.Parsers;

import com.BestBot.Core.DSMessageSender.MessageSender;
import com.BestBot.Core.Entity.ItemEntity;
import com.BestBot.Core.Repository.ItemEntityRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TestParser implements CrossoutdbParser{

    private final String api;

    private MessageSender messageSender;

    private ItemEntityRepository itemEntityRepository;

    private JDA jda;

    private List<ItemEntity> itemList;

    public TestParser(@Value("${getItemApi}") String api, ItemEntityRepository itemEntityRepositoryNew, MessageSender messageSender, JDA jda) {
        this.api = api;
        this.itemEntityRepository = itemEntityRepositoryNew;
        this.messageSender = messageSender;
        this.jda = jda;
        this.itemList = new ArrayList<>();
    }

    @Bean
    @Scheduled(fixedDelay = 300000)
    void saveEtities(){
        testTest().stream()
                .filter(e -> e instanceof ItemEntity)
                .forEach(e -> itemEntityRepository.save((ItemEntity) e));
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

    public List<Object> testTest(){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeAll;

        List<Object> items = new ArrayList<>();

        messageSender.sengMessage(jda, ("db renew START " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss.AAAA"))));
        try{
            jsonNodeAll = objectMapper.readTree(readFromApi());
            Flux.fromIterable(jsonNodeAll)
                    .map(jsonNode -> {
                                ItemEntity e = new ItemEntity();
                                e.setId(jsonNode.get("id").asLong());
                                e.setName(jsonNode.get("name").asText());
                                e.setCategory(jsonNode.get("categoryName").asText());
                                e.setSellPrice(jsonNode.get("sellPrice").asInt());
                                e.setBuyPrice(jsonNode.get("buyPrice").asInt());
                                e.setSellOffers(jsonNode.get("sellOffers").asInt());
                                e.setBuyOrders(jsonNode.get("buyOrders").asInt());
                                return e;
                            }
                    )
                    .subscribe(items::add);
        }catch (Exception e){
            messageSender.sengMessage(jda, ("db renew ERROR " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss.AAAA"))));
        }
        messageSender.sengMessage(jda, ("db renew COMPLETE " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss.AAAA"))));

        this.itemList = itemList.stream()
                .filter(Objects::nonNull)
                .toList();

        this.itemList.forEach(e-> System.out.println(e.getClass()));
        return items;
    }
}
