package com.BestBot.Core.Controller;

import com.BestBot.Core.Entity.ItemEntity;
import com.BestBot.Core.Parsers.TestParser;
import com.BestBot.Core.Repository.ItemEntityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
@RequestMapping("/users")
public class UserController {
    @Value("${getItemApi}")
    private String api;

    private List<ItemEntity> itemList;

    @GetMapping("/")
    public ResponseEntity getUsers(List<ItemEntity> itemList){
        String string = itemList.get(0).toString();

        try{
            return ResponseEntity.ok(string);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("error");
        }
    }
}
