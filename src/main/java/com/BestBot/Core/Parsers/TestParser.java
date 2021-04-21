package com.BestBot.Core.Parsers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Stream;

@Service
@Component
public class TestParser implements CrossoutdbParser{

    private final String api;

    public TestParser(@Value("${api}") String api) {
        this.api = api;
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

    @Override
    public String testTest(){
        String apiString = readFromApi();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        List<Flux> list = new ArrayList<>();
        JsonNode jsonNodeAll;
        JsonNode jsonNode1;
        JsonParser parser;


        try{
            jsonNodeAll = objectMapper.readTree(readFromApi());
//            map = objectMapper.readValue(readFromApi(), new TypeReference<Map<String,Object>>() {});
//            list = List.of(apiString.split(","));
//            jsonNode1 = jsonNodeAll;
//            System.out.println(jsonNodeAll.getNodeType());
//            jsonNode1.elements().forEachRemaining(e-> System.out.println(e.get("categoryName")));

            Flux.fromIterable(jsonNodeAll)
                    .filter(jsonNode -> jsonNode.get("categoryName").asText().equalsIgnoreCase("resources"))
                    .filter(e->e.get("amount").asText().equalsIgnoreCase("100"))
                    .checkpoint()
                    .subscribe(System.out::println);


        }catch (Exception e){
            e.printStackTrace();
        }

        return "testTest end";
    }
}
