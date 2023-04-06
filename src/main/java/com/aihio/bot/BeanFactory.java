package com.aihio.bot;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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
    public WebHookBot springWebhookBot(SetWebhook setWebhook) {
        logger.info("Registering webhook bot");
        var bot = new WebHookBot(botToken, botName, botPath);
        try {
            var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            bot.setWebhook(setWebhook);
            telegramBotsApi.registerBot(bot,setWebhook);
        } catch (TelegramApiException e) {
            logger.error("Error registering bot", e);
        }
        return bot;
    }
}
