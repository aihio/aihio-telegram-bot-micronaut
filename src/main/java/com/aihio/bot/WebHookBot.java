package com.aihio.bot;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WebHookBot extends TelegramWebhookBot {

    private static final Logger logger = LoggerFactory.getLogger(WebHookBot.class.getName());

    private final String botUsername;

    private final String bothPath;

    public WebHookBot(String botToken, String botUsername, String botPath) {
        super(botToken);
        this.botUsername=botUsername;
        this.bothPath=botPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            logger.info("Message received");
//            var message = new SendMessage(); // Create a SendMessage object with mandatory fields
//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText(update.getMessage().getText());
//            try {
//                execute(message); // Call method to send the message
//                logger.info("Message sent");
//            } catch (TelegramApiException e) {
//                logger.error("Error during message processing", e);
//            }
//        }
        logger.info("Received call");
        return new SendMessage(update.getMessage().getChatId().toString(), "No data");
    }

    @Override
    public String getBotPath() {
        return bothPath;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
