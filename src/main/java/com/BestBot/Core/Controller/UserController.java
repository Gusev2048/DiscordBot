package com.BestBot.Core.Controller;

import com.BestBot.Core.Service.TestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private TestParser testParser;

    @Autowired
    private void setTestParser (TestParser testParser){
        this.testParser = testParser;
    }

    @GetMapping("/get0")
    public ResponseEntity getUsers(){
        try{
            return ResponseEntity.ok(getString());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("error");
        }
    }

    private String getString(){
        return testParser.getItemList().get(27).toString();
    }
}
