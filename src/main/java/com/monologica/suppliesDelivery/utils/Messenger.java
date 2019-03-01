package com.monologica.suppliesDelivery.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Messenger {

    ColourFormatter formatter;

    public Messenger() {
        this.formatter = new ColourFormatter();
    }

    public void sendActionBar(CommandSender cs, String message) {
        if (cs instanceof Player) {
            Player p = (Player)cs;
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(formatter.format(message)));
        } else {
            cs.sendMessage(formatter.format(message));
        }
    }

    public void sendMessages(CommandSender cs, List<String> message) {
        for(String s: message) {
            cs.sendMessage(formatter.format(s));
        }
    }
}
