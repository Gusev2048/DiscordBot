package com.example.demo;


import com.austinv11.servicer.Service;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageCreateListener extends ListenerAdapter {

    public Class<MessageCreateListener> getEventType() {
        return MessageCreateListener.class;
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent eventMessage) {

        System.out.println("ololo2");


        List<MessageAction> list = new ArrayList<>();


       Mono.just(eventMessage)
//               .filter(message -> message.getAuthor().isBot())
                .filter(event -> event.getMessage().getContentDisplay().equalsIgnoreCase("!todo"))
                .map(MessageReceivedEvent::getChannel)
                .map(channel -> channel.sendMessage("ololo"))
                .flatMap(RestAction::queue)
               .checkpoint(String.valueOf(list.add((MessageAction) eventMessage)))
               .then();

        list
                .forEach(RestAction::queue);
        System.out.println("ololo3");

    }

}
