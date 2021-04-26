package com.BestBot.RESTController;

import com.BestBot.Service.TestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/test")
public class TestController {

    private TestParser testParser;

    @Autowired
    private void setTestParser (TestParser testParser){
        this.testParser = testParser;
    }

    @GetMapping("/get0")
    public ResponseEntity getUsers(){
        try{
            return ResponseEntity.ok(getString("27"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("error");
        }
    }

    @PostMapping(path = "/set0")
    public void getData(@RequestBody String string) {
        System.out.println(string);
    }

    private String getString(String id){
        return testParser.getItems().get(id).toString();
    }
}
