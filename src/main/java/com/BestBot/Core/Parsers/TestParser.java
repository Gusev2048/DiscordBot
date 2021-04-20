package com.BestBot.Core.Parsers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class TestParser implements CrossoutdbParser{

    private final String api;

    public TestParser(@Value("${api}") String api) {
        this.api = api;
    }

    @Override
    public String getString() {
        return api;
    }
}
