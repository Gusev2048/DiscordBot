package com.BestBot.Service;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Service
@PropertySource("classpath:commands.properties")
public class DiscordCommandExecutor {

    @Value("${firstCommand}")
    private String firstCommand;
    @Value("${secondCommand}")
    private String secondCommand;

    public MessageEmbed getResponse(String message) {
        List<String> messageBody = Arrays.stream(message.split(" ")).toList();

        messageBody.stream()
                .filter(e -> e.equalsIgnoreCase(firstCommand))
                .toString();

        messageBody.forEach(System.out::println);

        return buildMessage(message);
    }

    public MessageEmbed buildMessage(String string){

        EmbedBuilder builder = new EmbedBuilder();
        return builder.setTitle("ololoTitle")
                            .setColor(Color.CYAN)
                            .addField("ololoFieldName", "ololo>>" + string + "<<ololo", true)
                            .setFooter("ololoFooter")
                            .build();
    }
}
