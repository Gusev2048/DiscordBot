package com.BestBot.Core.Parsers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<String> testTest(){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeAll;

        List<String> sashka = null;

        try{
            jsonNodeAll = objectMapper.readTree(readFromApi());
            Flux.fromIterable(jsonNodeAll)
                    .filter(jsonNode -> jsonNode.get("categoryName").asText().equalsIgnoreCase("resources"))
                    .filter(e->e.get("amount").asText().equalsIgnoreCase("100"))
                    .subscribe(System.out::println);

        }catch (Exception e){
            e.printStackTrace();
        }
        return sashka;
    }
}
