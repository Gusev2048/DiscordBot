package com.BestBot.Core.Configuration;

import com.BestBot.Core.Entity.ItemEntity;
import com.BestBot.Core.Parsers.CrossoutdbParser;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:token.properties")
public class BotConfiguration {

    private final String token;

    public BotConfiguration(@Value("${token}") String token) {
        this.token = token;
    }

    @Bean
    public JDA gatewayDiscordClient(List<ListenerAdapter> eventListeners, List<CrossoutdbParser> parsersList) throws LoginException, InterruptedException {

        JDA jda = JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_EMOJIS, GatewayIntent.GUILD_PRESENCES)
                .build()
                .awaitReady();

        for(ListenerAdapter listener : eventListeners) {
           jda.addEventListener(listener);
        }

        parsersList.forEach(System.out::println);
        return jda;
    }
}