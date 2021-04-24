package com.BestBot.Service;

import com.BestBot.Entity.ItemEntity;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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

    private TestParser testParser;
    private MessageSender messageSender;

    public DiscordCommandExecutor(TestParser testParser, MessageSender messageSender) {
        this.testParser = testParser;
        this.messageSender = messageSender;
    }

//    public MessageEmbed getResponse(String message) {
//        List<String> messageBody = Arrays.stream(message.split(" ")).toList();
//
////        messageBody.stream()
////                .filter(e -> e.equalsIgnoreCase(firstCommand))
////                .filter(e -> e.equalsIgnoreCase(secondCommand))
////                .forEach(e -> return buildMessage(e));
//
//        messageBody.forEach(System.out::println);
//
//        return buildMessage(message);
//    }

    public void buildMessage(TextChannel textChannel, ItemEntity itemEntity){

        EmbedBuilder builder = new EmbedBuilder();
        textChannel.sendMessage(builder.setTitle(itemEntity.getName() + " " + itemEntity.getCategory())
                            .setColor(Color.CYAN)
                            .addField("ololoFieldName", itemEntity.toString(), true)
                            .setFooter(itemEntity.getLastUpdateTime())
                            .build()).queue();
    }

    public void setCommand(MessageReceivedEvent event) {
        TextChannel textChannel = event.getTextChannel();
        Message message = event.getMessage();

        Mono.just(message.getContentDisplay().split(" "))
                .filter(e -> e[0].equalsIgnoreCase(firstCommand))
//                .filter(e -> e[1].equalsIgnoreCase(secondCommand))
                .checkpoint("ololo")
                .subscribe(e -> buildMessage(textChannel, testParser.getItems().get(e[1])));
//                .then(e -> messageSender.sengMessage(textChannel, testParser.getItems().get(e[0]).toString()));

    }
}
