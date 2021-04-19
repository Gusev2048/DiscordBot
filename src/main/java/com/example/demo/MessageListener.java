package com.example.demo;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import reactor.core.publisher.Mono;

public abstract class MessageListener extends ListenerAdapter{
    public Mono<Void> processCommand(MessageReceivedEvent eventMessage) {
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().isBot())
                .filter(event -> event.getMessage().getContentDisplay().equalsIgnoreCase("!todo"))
                .map(MessageReceivedEvent::getChannel)
                .map(channel -> channel.sendMessage("ololo"))
                .then();
    }
}
