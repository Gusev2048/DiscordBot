package com.BestBot.RESTController;

import com.BestBot.Service.TestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class TestController {

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

    @PostMapping(path = "/set0", produces = MediaType.APPLICATION_XML_VALUE)
    public void getData(@RequestBody String string) {
        System.out.println(string);
    }

    private String getString(){
        return testParser.getItems().get(27).toString();
    }
}
