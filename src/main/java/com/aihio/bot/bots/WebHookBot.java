package com.aihio.bot.bots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WebHookBot extends TelegramWebhookBot {

    private static final Logger logger = LoggerFactory.getLogger(WebHookBot.class.getName());

    private final String botUsername;

    private final String bothPath;

    public WebHookBot(String botToken, String botUsername, String botPath) {
        super(botToken);
        this.botUsername = botUsername;
        this.bothPath = botPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        logger.info("Received call");
        if (update.hasMessage() && update.getMessage().hasText()) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    update.getMessage().getText());
        }
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
