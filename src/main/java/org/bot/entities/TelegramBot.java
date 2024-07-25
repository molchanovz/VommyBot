//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.bot.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bot.components.BotCommands;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    String cashMsg = "";

    public TelegramBot(String botToken) {
        super(botToken);

        try {
            this.execute(new SetMyCommands(BotCommands.LIST_OF_COMMANDS, new BotCommandScopeDefault(), (String) null));
            this.sendMsg("406363099", "Привет, я снова с тобой!");
        } catch (TelegramApiException var3) {
            TelegramApiException e = var3;
            System.out.println(e.getMessage());
        }

    }

    public void onUpdateReceived(Update update) {
        String name = String.valueOf(update.getMessage().getFrom().getFirstName());
        String surName = String.valueOf(update.getMessage().getFrom().getLastName());
        String chatId = String.valueOf(update.getMessage().getChatId());
        String userName = update.getMessage().getFrom().getUserName();
        String text = update.getMessage().getText();
        String message;
        //-4031969356
        if (chatId.equals("-4239112578")) {
            message = update.getMessage().getReplyToMessage().getText();
            String[] str = message.split("'");
            this.sendMsg(str[1], text);
        } else if (update.getMessage().getText().contains("/start")) {
            if (name.equals((Object) null)) {
                name = "";
            }

            this.sendMsg(String.valueOf(update.getMessage().getChatId()), "Добрый день " + name + "! Я помощник компании *Трейд-Плюс*.\nЕсли у вас есть вопросы или возникла проблема с заказом,\nнажмите на кнопку ниже \ud83d\udc47\ud83c\udffb");
        } else {
            message = "";
            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("Обращения по гарантии")) {
                    System.out.println("@"+userName+" создает обращение по гарантии");
                    this.cashMsg = "Новое обращение по гарантии";
                    this.sendMsg(String.valueOf(update.getMessage().getChatId()), "Если у вас возникли проблемы с нашим товаром, пожалуйста, напишите подробнее о проблеме.\nМы готовы помочь вам решить её.");
                    this.sendMsg(String.valueOf(update.getMessage().getChatId()), "*Обязательно* укажите:\n\n• Название товара\n• Маркетплейс, на котором покупали\n• Номер заказа (если есть)\n• Дату заказа.\n\n*Так мы сможем вам помочь в полной мере!*");
                } else if (update.getMessage().getText().equals("Вопрос по товару")) {
                    this.cashMsg = "Новый вопрос по товару";
                    System.out.println("@"+userName+" создает вопрос по товару");
                    this.sendMsg(String.valueOf(update.getMessage().getChatId()), "Подробно напишите свой вопрос.");
                    this.sendMsg(String.valueOf(update.getMessage().getChatId()), "В сообщении не забудьте указать:\n\n• Название товара\n• Маркетплейс, на котором покупали товар");
                } else if (update.getMessage().getText().equals("Связаться с продавцом")) {
                    this.cashMsg = "С нами хотят связаться";
                    System.out.println("@"+userName+" хочет с нами связаться");
                    this.sendMsg(String.valueOf(update.getMessage().getChatId()), "Я могу помочь вам связаться с нашим отделом продаж. Что вас интересует?\n");
                } else {
                    char[] userChars = userName.toCharArray();
                    ArrayList<Character> newUserChars = new ArrayList();
                    char[] var10 = userChars;
                    int var11 = userChars.length;

                    for (int var12 = 0; var12 < var11; ++var12) {
                        char c = var10[var12];
                        if (c == '_') {
                            newUserChars.add('\\');
                        }

                        newUserChars.add(c);
                    }

                    userName = (String) newUserChars.stream().map((e) -> {
                        return e.toString();
                    }).collect(Collectors.joining());
                    this.sendMsg(chatId, "Передал ваше сообщение менеджеру, ожидайте ответа \ud83d\ude0a");
                    message = this.cashMsg + "\n" + name + " " + surName + "\nChatId - '" + chatId + "'\nНик тг - @" + userName + "\n\n" + text;
                    System.out.println(message);
                    this.sendMsg("-4239112578", message);
                }
            } else {
                System.out.println("Не получилось");
            }
        }

    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId);
        message.setText(s);
        this.setButtons_1(message);

        try {
            this.execute(message);
        } catch (TelegramApiException var5) {
            TelegramApiException e = var5;
            throw new RuntimeException(e);
        }
    }

    public String getBotUsername() {
        return "TradePlusHelper_bot";
    }

    public synchronized void setButtons_1(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("Обращения по гарантии");
        keyboardFirstRow.add(keyboardButton);
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Вопрос по товару"));
        KeyboardRow keyboardThreeRow = new KeyboardRow();
        keyboardThreeRow.add(new KeyboardButton("Связаться с продавцом"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThreeRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public synchronized void setButtons_2(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("Указать почту");
        keyboardFirstRow.add(keyboardButton);
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public synchronized void setButtons_nothing(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
