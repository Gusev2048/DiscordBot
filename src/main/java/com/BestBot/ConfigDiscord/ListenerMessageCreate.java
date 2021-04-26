package com.BestBot.ConfigDiscord;

import com.BestBot.Service.DiscordCommandExecutor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ListenerMessageCreate extends ListenerAdapter {

    @Value("${prefix}")
    private String prefix;

    private DiscordCommandExecutor discordCommandExecutor;

    public ListenerMessageCreate(DiscordCommandExecutor discordCommandExecutor) {
        this.discordCommandExecutor = discordCommandExecutor;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent eventMessage) {
       Mono.just(eventMessage)
                .filter(message -> !message.getAuthor().isBot())
                .filter(event -> event.getMessage().getContentDisplay().startsWith(prefix))
                .subscribe(event -> discordCommandExecutor.setCommand(event));
    }
}
