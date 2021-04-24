package com.BestBot.Service;

import com.BestBot.Entity.ItemEntity;
import com.BestBot.RESTController.CNCController;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.*;

@Service
@PropertySource("classpath:commands.properties")
public class DiscordCommandExecutor {

    @Value("${firstCommand}")
    private String firstCommand;
    @Value("${secondCommand}")
    private String secondCommand;

    private TestParser testParser;
    private MessageSender messageSender;
    private CNCController cncController;

    public DiscordCommandExecutor(TestParser testParser, MessageSender messageSender, CNCController cncController) {
        this.testParser = testParser;
        this.messageSender = messageSender;
        this.cncController = cncController;
    }

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

        switch (event.getMessage().getContentDisplay().split(" ")[0]) {
            case ";item" -> Mono.just(message.getContentDisplay().split(" "))
                    .subscribe(e -> buildMessage(textChannel, testParser.getItems().get(e[1])));
            case ";skynet" -> Mono.just(message.getContentDisplay().split(" "))
                    .subscribe(e -> cncController.sendToCNC(textChannel.getId(), message.getContentDisplay().replace(";skynet ", "")));
        }
    }
}
