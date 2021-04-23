package com.BestBot.Core.Service;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@PropertySource("classpath:token.properties")
public class MessageSender {
    private final long statusChannelId;
    private final long statusGuildId;
    private JDA jda;

    public MessageSender(@Value("${testChannelID}") long statusChannelId, @Value("${testGuildID}") long statusGuildId, JDA jda) {
        this.statusChannelId = statusChannelId;
        this.statusGuildId = statusGuildId;
        this.jda = jda;
    }

    public void sengMessage(String text){
        TextChannel statusChannel = Objects.requireNonNull(jda.getGuildById(statusGuildId)).getTextChannelById(statusChannelId);
        Objects.requireNonNull(statusChannel).sendMessage(text).queue();
    }

    public void sengMessage(TextChannel channel, String text){
        Objects.requireNonNull(channel).sendMessage(text).queue();
    }
}
