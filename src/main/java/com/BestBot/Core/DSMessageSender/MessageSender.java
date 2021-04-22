package com.BestBot.Core.DSMessageSender;

import com.BestBot.Core.Configuration.BotConfiguration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static reactor.core.publisher.ParallelFlux.from;

@Service
@Configuration
public class MessageSender {
    private JDA jda;
    private long channelId;
    private long guildId;


    public MessageSender(@Value("${testChannelID}") long channelId, @Value("${testGuildID}") long guildId) {

        this.jda = jda;
//        jda.getRegisteredListeners().forEach(System.out::println);
        this.channelId = channelId;
        this.guildId = guildId;
    }

    public void sengMessage(String text){
        TextChannel channel = Objects.requireNonNull(jda.getGuildById(guildId)).getTextChannelById(channelId);
        Objects.requireNonNull(channel).sendMessage(text).queue();
    }
}
