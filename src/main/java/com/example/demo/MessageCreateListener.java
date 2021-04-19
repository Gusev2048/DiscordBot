package com.example.demo;


import com.austinv11.servicer.Service;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListener extends MessageListener implements EventListener<MessageReceivedEvent> {

    @Override
    public Class<MessageReceivedEvent> getEventType() {
        return MessageReceivedEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageReceivedEvent event) {
        System.out.println("ololo2");
        return processCommand(event);
    }
}
