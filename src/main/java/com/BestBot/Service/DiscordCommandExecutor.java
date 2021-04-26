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
import java.util.stream.Collectors;

@Service
public class DiscordCommandExecutor {

    Map<String, Color> colorMap;

    private TestParser testParser;
    private CNCController cncController;
    private EntityFinder entityFinder;

    public DiscordCommandExecutor(TestParser testParser, CNCController cncController, EntityFinder entityFinder1) {
        this.testParser = testParser;
        this.cncController = cncController;
        this.entityFinder = entityFinder1;
        this.colorMap = new HashMap<>();

        colorMap.put("1", Color.decode("#FFFAFA"));
        colorMap.put("2", Color.BLUE);
        colorMap.put("6", Color.CYAN);
        colorMap.put("3", Color.MAGENTA);
        colorMap.put("4", Color.decode("#ffd700"));
        colorMap.put("5", Color.decode("#FF9000"));
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

    public void buildMessageAll(TextChannel textChannel, String string){

        EmbedBuilder builder = new EmbedBuilder();
        textChannel.sendMessage(builder.setTitle("All items for category:")
                .setColor(colorMap.get(Color.RED))
                .addField("", string, true)
                .build()).queue();
    }

    public void setCommand(MessageReceivedEvent event) {
        TextChannel textChannel = event.getTextChannel();
        Message message = event.getMessage();

        switch (event.getMessage().getContentDisplay().split(" ")[0].toLowerCase()) {
            case ";item" -> {
                Mono.just(message.getContentDisplay().split(" "))
                        .subscribe(e -> buildMessage(textChannel, entityFinder.getItemEntity(e[1]).isPresent() ? entityFinder.getItemEntity(e[1]).get() : testParser.getItems().get("Hurricane")));
            }
            case ";skynet" -> {
                Mono.just(message.getContentDisplay().split(" "))
                        .subscribe(e -> cncController.sendToCNC(textChannel.getId(), message.getContentDisplay().replace(";skynet ", "")));
            }
            case ";itemlist" -> {

                Mono.just(message.getContentDisplay().split(" "))
                        .subscribe(e -> buildMessageAll(textChannel, entityFinder.getCategoryItems(e[1]).isPresent() ? entityFinder.getCategoryItems(e[1]).get().stream()
                                                                                                                                    .filter(r -> r.getRarity().toLowerCase().contains(e[2].toLowerCase()))
                                                                                                                                    .map(ItemEntity::getName)
                                                                                                                                    .collect(Collectors.joining("\n")) : "No such category("));
            }
        }
    }
}
