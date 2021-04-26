package com.BestBot.ConfigDiscord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.security.auth.login.LoginException;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:token.properties")
public class MessageSenderJDA {

    private final String token;
    private JDA jda;

    public MessageSenderJDA(@Value("${token}") String token) {
        this.token = token;
    }

    @Bean
    public void getNewMessageSenderJDA() throws LoginException, InterruptedException {
        this.jda = JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_EMOJIS, GatewayIntent.GUILD_PRESENCES)
                .build()
                .awaitReady();
    }

    public JDA getJDA(){
        return this.jda;
    }
}