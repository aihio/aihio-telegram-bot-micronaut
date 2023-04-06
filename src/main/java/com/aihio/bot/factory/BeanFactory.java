package com.aihio.bot.factory;

import com.aihio.bot.bots.WebHookBot;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Factory
public class BeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class.getName());

    @Property(name = "bot.name")
    private String botName;
    @Property(name = "bot.token")
    private String botToken;
    @Property(name = "bot.path")
    private String botPath;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(botPath).build();
    }

    @Bean
    public WebHookBot webHookBot(SetWebhook setWebhook) {
        var bot = new WebHookBot(botToken, botName, "/");
        try {
            logger.debug("Initializing WebHookBot...");
            bot.setWebhook(setWebhook);
        } catch (TelegramApiException e) {
            logger.error("Failed to initialize WebHookBot!", e);
            throw new RuntimeException(e);
        }
        return bot;
    }
}
