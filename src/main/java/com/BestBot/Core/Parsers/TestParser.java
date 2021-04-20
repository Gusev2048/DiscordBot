package com.BestBot.Core.Parsers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Component
public class TestParser implements CrossoutdbParser{

    private String api;

    private LocalDateTime timeLastApiResponse; //возраст курса доллара

    public TestParser(@Value("${api}") String api) {
        this.api = api;
    }

    @Override
    public String getString() {
        return api;
    }
}
