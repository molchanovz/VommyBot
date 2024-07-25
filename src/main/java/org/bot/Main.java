package org.bot;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.bot.Protection;
import org.bot.entities.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    static String token;

    public Main() {
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBot bot = new TelegramBot(token);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            System.out.println(botsApi.registerBot(bot));
        } catch (TelegramApiException var3) {
            TelegramApiException e = var3;
            throw new RuntimeException(e);
        }
    }

    static {
        token = Protection.token;
    }
}
