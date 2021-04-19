package com.example.demo;

import net.dv8tion.jda.api.events.Event;
import reactor.core.publisher.Mono;

public interface EventListener<T extends Event> {

    Class<T> getEventType();
    Mono<Void> execute(T event);

}
