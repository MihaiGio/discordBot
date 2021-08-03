package ro.salty.saltyBot.listeners.impl;

import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;
import ro.salty.saltyBot.listeners.PingListener;

@Component
public class PingListenerImpl implements PingListener {

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if(messageCreateEvent.getMessageContent().equals(".ping")){
            messageCreateEvent.getChannel().sendMessage("Pong!");
        }
        if(messageCreateEvent.getMessageContent().equals(".melon")){
            messageCreateEvent.getChannel().sendMessage("What did the melon say when his lawn looked dry.\n" +
                    "Guess it's time to watermalawn.");
        }
    }
}
