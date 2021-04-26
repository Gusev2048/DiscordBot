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
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:commands.properties")
public class DiscordCommandExecutor {

    @Value("${firstCommand}")
    private String firstCommand;
    @Value("${secondCommand}")
    private String secondCommand;

    Map<String, Color> colorMap;

    private TestParser testParser;
    private CNCController cncController;

    public DiscordCommandExecutor(TestParser testParser, CNCController cncController) {
        this.testParser = testParser;
        this.cncController = cncController;
        this.colorMap = new HashMap<>();

        colorMap.put("1", Color.decode("#FFFAFA"));
        colorMap.put("2", Color.BLUE);
        colorMap.put("6", Color.CYAN);
        colorMap.put("3", Color.MAGENTA);
        colorMap.put("4", Color.YELLOW);
        colorMap.put("5", Color.orange);
    }

    public void buildMessage(TextChannel textChannel, ItemEntity itemEntity){

        EmbedBuilder builder = new EmbedBuilder();
        textChannel.sendMessage(builder.setTitle(itemEntity.getName())
                            .setColor(colorMap.get(itemEntity.getRarityId()))
                            .addField("Category: " + itemEntity.getCategory() + ". Rarity: " + itemEntity.getRarity(), "", false)
                            .addField("Sell price: " + itemEntity.getSellPrice()/100, "Sell offers: " + itemEntity.getSellOffers(), true)
                            .addField("Buy price: " + itemEntity.getBuyPrice()/100, "Buy offers: " + itemEntity.getBuyOrders(), true)
                            .setFooter("Updated: " + itemEntity.getLastUpdateTime())
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
