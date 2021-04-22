package com.BestBot.Core.DSMessageSender;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@PropertySource("classpath:token.properties")
public class MessageSender {
    private long channelId;
    private long guildId;

    public MessageSender(@Value("${testChannelID}") long channelId, @Value("${testGuildID}") long guildId) {

        this.channelId = channelId;
        this.guildId = guildId;
    }

    public void sengMessage(JDA jda, String text){
        TextChannel channel = Objects.requireNonNull(jda.getGuildById(guildId)).getTextChannelById(channelId);
        Objects.requireNonNull(channel).sendMessage(text).queue();
    }
}
