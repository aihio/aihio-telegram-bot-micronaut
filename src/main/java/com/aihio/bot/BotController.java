package com.aihio.bot;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/")
public class BotController {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String greetings(){
        return "Hello!";
    }
}
