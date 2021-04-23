package com.BestBot.Core.Controller;

import com.BestBot.Core.Service.MessageSender;
import com.BestBot.Core.Service.TestParser;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DiscordRequestController {
    private TestParser testParser;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private void setTestParser (TestParser testParser){
        this.testParser = testParser;
    }

    public void sendMessage(TextChannel textChannel, String id){
        String text = testParser.getItemList()
                                .stream()
                                .filter(e -> e.getId().toString().equalsIgnoreCase(id))
                                .toString();

        messageSender.sengMessage(textChannel, text);
    }
}
