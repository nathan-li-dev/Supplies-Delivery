package com.monologica.suppliesDelivery.utils;

import net.md_5.bungee.api.ChatColor;

public class ColourFormatter implements TextFormatter {
    public String format(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
