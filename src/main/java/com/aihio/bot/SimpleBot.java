package com.aihio.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SimpleBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(SimpleBot.class.getName());
    private final String botUsername;

    public SimpleBot(String botUsername, String botToken) {
        super(botToken);
        this.botUsername = botUsername;

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            logger.info("Message received");
            var message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            try {
                execute(message); // Call method to send the message
                logger.info("Message sent");
            } catch (TelegramApiException e) {
                logger.error("Error during message processing", e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
