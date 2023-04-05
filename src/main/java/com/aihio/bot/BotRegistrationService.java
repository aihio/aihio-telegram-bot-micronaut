package com.aihio.bot;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceReadyEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Singleton
public class BotRegistrationService implements ApplicationEventListener<ServiceReadyEvent> {
    private static final Logger logger = LoggerFactory.getLogger(BotRegistrationService.class.getName());

    @Property(name = "bot.name")
    private String botName;

    @Property(name = "bot.token")
    private String botToken;
    @Property(name = "bot.path")
    private String botPath;

    @Override
    public void onApplicationEvent(ServiceReadyEvent event) {
        try {
            logger.info("Registering webhook bot");
            var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

            telegramBotsApi.registerBot(new WebHookBot(botToken, botName, botPath),
                    SetWebhook.builder().url(botPath).build());
        } catch (TelegramApiException e) {
            logger.error("Error registering bot", e);
        }
    }
}
