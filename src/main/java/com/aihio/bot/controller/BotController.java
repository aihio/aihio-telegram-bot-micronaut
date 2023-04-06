package com.aihio.bot.controller;

import com.aihio.bot.bots.WebHookBot;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Controller
public class BotController {

    private final WebHookBot webHookBot;

    public BotController(WebHookBot webHookBot) {
        this.webHookBot = webHookBot;
    }

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String greetings(){
        return "AihioBot is up!";
    }

    @Post("callback")
    public BotApiMethod<?> onUpdateReceived(@Body Update update){
        return webHookBot.onWebhookUpdateReceived(update);
    }
}
