package com.BestBot.ConfigDiscord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:token.properties")
public class ConfigJDA {

    private final String token;
    private final List<ListenerAdapter> eventListeners;

    public ConfigJDA(@Value("${token}") String token, List<ListenerAdapter> eventListeners) {
        this.token = token;
        this.eventListeners = eventListeners;
    }

    @Bean
    public JDA getNewJDA() throws LoginException, InterruptedException {
        return JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_EMOJIS, GatewayIntent.GUILD_PRESENCES)
                .build()
                .awaitReady();
    }

    @Bean
    ApplicationRunner listenersAdder(JDA jda){
        return args -> {
            for(ListenerAdapter listener : eventListeners) {
                jda.addEventListener(listener);
            }
        };
    }
}