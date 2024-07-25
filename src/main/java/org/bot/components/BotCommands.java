//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.bot.components;

import java.util.List;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(new BotCommand("/start", "start bot"));
    String HELP_TEXT = "This bot will help to count the number of messages in the chat. The following commands are available to you:\n\n/start - start the bot\n/help - help menu";
}
