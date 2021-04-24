package com.BestBot.RESTController;

import com.BestBot.ConfigDiscord.MessageSenderJDA;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//http://localhost:8095/test/set0"
@RestController
@RequestMapping("/CNC")
public class CNCController {

    private MessageSenderJDA messageSenderJDA;

    public CNCController(MessageSenderJDA messageSenderJDA) {
        this.messageSenderJDA = messageSenderJDA;
    }

    public void sendToCNC(String channelId, String message){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8095/CNC/skynet/set0");
        try{
            post.setEntity(new StringEntity("{\"channelID\":\"" + channelId + "\",\"messageBody\":\"" + message + "\"}"));
            CloseableHttpResponse response = client.execute(post);
            response.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @PostMapping(path = "/skynet/set0", produces = MediaType.APPLICATION_XML_VALUE)
    public void getData(@RequestBody String string) {
        System.out.println(string);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonNode.fieldNames().forEachRemaining(System.out::println);
        messageSenderJDA.getJDA()
                .getTextChannelById(jsonNode.get("channelID").asText())
                .sendMessage(jsonNode.get("messageBody").asText())
                .queue();
    }
}
