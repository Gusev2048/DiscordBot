package com.BestBot.Core.Configuration.Listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MessageCreateListener extends ListenerAdapter {

    @Value("${parol}")
    private String parol;
    @Value("${otzuv}")
    private String otzuv;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent eventMessage) {

       Mono.just(eventMessage)
                .filter(message -> !message.getAuthor().isBot())
                .filter(event -> event.getMessage().getContentDisplay().equalsIgnoreCase(parol))
                .map(MessageReceivedEvent::getChannel)
                .map(channel -> channel.sendMessage(otzuv))
                .subscribe(MessageAction::queue);
    }
}
