package com.BestBot.Core.Configuration;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
@PropertySource("classpath:token.properties")
public class BotConfiguration {

   @Value("${token}")
    private String token;

    public BotConfiguration() {
        List<ListenerAdapter> eventListeners;
    }

    @Bean
    public <T extends Event> JDA gatewayDiscordClient(List<ListenerAdapter> eventListeners) throws LoginException, InterruptedException {
        JDA jda = JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .build()
                .awaitReady();

        for(ListenerAdapter listener : eventListeners) {
           jda.addEventListener(listener);
        }
        return jda;
    }
}