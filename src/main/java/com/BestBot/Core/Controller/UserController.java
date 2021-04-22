package com.BestBot.Core.Controller;

import com.BestBot.Core.Parsers.TestParser;
import com.BestBot.Core.Repository.ItemEntityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Value("${getItemApi}")
    private String api;

    @GetMapping("/")
    public ResponseEntity getUsers(){
        try{
            return ResponseEntity.ok("new TestParser(api).testTest().toString()");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("error");
        }
    }
}
